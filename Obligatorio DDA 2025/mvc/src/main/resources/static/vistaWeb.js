/* 
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Other/javascript.js to edit this template
 */
// La pÃ¡gina que incluya esta lib debe (opcionalmente) cargar estas variables 
var urlIniciarVista = null;
var parametrosInicioVista = "";
var urlCierreVista = null;
var parametrosCierreVista = "";
var isPrimerSubmitFinalizado = false;
var prefijoNombreFuncionProcesoResultado = "mostrar_";

// Se ejecuta al final de la carga de la pÃ¡gina para avisar al controlador que la vista esta cargada
document.addEventListener("DOMContentLoaded", function () {
    if (urlIniciarVista !== null) {
        submit(urlIniciarVista, parametrosInicioVista,'GET');
    }

//Cuando se baja la pagina le avisa al controlador que la vista no estÃ¡ activa
    if (urlCierreVista !== null) {
        window.addEventListener("beforeunload", function () {
            navigator.sendBeacon(urlCierreVista, parametrosCierreVista);
        });
    }
    //Quitar este metodo, ya no es necesario...revisar antes 
    try {
        ejecutarCodigoDocumentReady();
    } catch (e) {}
});

// Ejecuta un endpoint usando fetch y envÃ­a datos en formato URL Encoded
function submit(endPointUrl, urlEncodedData, method = 'POST') {
    // Si el mÃ©todo es GET, los parÃ¡metros van en la URL y no en el body
    let fetchUrl = endPointUrl;
    let fetchOptions = {
        method: method,
        headers: {
            'Content-Type': 'application/x-www-form-urlencoded'
        }
    };

    if (method.toUpperCase() === 'GET') {
        if (urlEncodedData && urlEncodedData.length > 0) {
            fetchUrl += (fetchUrl.includes('?') ? '&' : '?') + urlEncodedData;
        }
        // GET no debe tener body
        delete fetchOptions.headers; // No es necesario el Content-Type en GET
    } else {
        fetchOptions.body = urlEncodedData;
    }

    fetch(fetchUrl, fetchOptions).then(async response => {
        const status = response.status;
        const text = await response.text();
        //Si el servidor responede con error
        if(status<200 || status >299){
            manejarError(status,text,endPointUrl,urlEncodedData);
            return;
        }
        //se ejecuta luego de finalizado el primer submit de la pagia
        //esto puede ser importante porque recien ahi se establece la sesion en el servidor (si es que aun no se ha establecido)
        //EJ: si quiero conectarSSE deberia esperar a que termine el primer subit si no quiero asumir que ya hay una sesion establecida
        if (!isPrimerSubmitFinalizado) {
            isPrimerSubmitFinalizado = true;
            try {
               primerSubmitFinalizado();
            } catch (e) {}
        }
        //si se produce una Excepcion de aplicacion se asume que el servidor responde con status 299
        if (status === 299) {
            try {
                excepcionDeAplicacion(text);//Metodo que puede estar definido en la pagina que incluya esta lib 
                                            //para personalizar el manejo del error de aplicacion   
            } catch (e) {
                alert(text); //Por defecto...
            }
            return;
        } 
        try {
            const json = JSON.parse(text);
            if (Array.isArray(json)) {
                 //si llega una coleccion de respuestas 
                  procesarResultadosSubmit(json);
            }else{
                try{
                   //si llega otro tipo de respuesta 
                  procesarRespuestaSubmit(text);
                } catch (e) {
                    console.error("No se encontro la funcion procesarRespuestaSubmit para la respuesta:",text);
                }
            }
             
        } catch (e) {
            console.error("Error procesar la respuesta:", e);
        }   
        
    }).catch(error => {
        //Si no se puede hacer el fetch
        manejarError(0,error.message,endPointUrl,urlEncodedData);
      
    });
}
function manejarError(status, text, url, data){
     try {
         //Definir para personalizar el manejo de errores en las paginas
         procesarErrorSubmit(status,text);
     } catch (e) {
                console.error("url:" + url + "  data: " + data);
                console.error("Error en submit:" + status, text);
                document.body.innerHTML = '';
                alert("Se produjo un error, detalles en consola");
     }
}

// Por cada respuesta llama a la funciÃ³n "mostrar_" correspondiente
//cambiando el valor de prefijoNombreFuncionProcesoResultado se puede personalizar el nombre 
//del prefijo de las funciones que procesan las respuestas del controlador
function procesarResultadosSubmit(listaRespuestas) {
    listaRespuestas.forEach(respuesta => {
        let nombreFuncion = prefijoNombreFuncionProcesoResultado + respuesta.id;
        
        if (typeof window[nombreFuncion] === 'function') {
            try{
                window[nombreFuncion](respuesta.parametro);
            }catch(e){
                console.error("Error en la funcion " + nombreFuncion + " : ", e);
            }
        } else {
            console.error("No estÃ¡ definida la funciÃ³n " + nombreFuncion + " para procesar la respuesta " + respuesta.id);
        }
    });
}