Prueba = (function () {

    function GuardarPrueba(){
        var promise = $.get({
            url: "/nspapp/Prueba",
            contentType: "application/json"
        });
        promise.then(function(data){
            VistaPrueba(data);
        }, function(error) {
            console.log(22);
        });
    }

    function VistaPrueba(data){
        for (let i = 0; i < data.split("%").length; i++) {
            data.split("%")[i].split("#");
            $("#Prueba").append("<li class='temporal'>"+data.split("%")[i].split("#")[0]+" "+data.split("%")[i].split("#")[1])
        }

    }



    function insertar(){
        var name = $("#Nombre").val()
        var desc = $("#Descripcion").val()
        var promise = $.get({
            url: "/nspapp/insertar",
            data: JSON.stringify(name+"!"+desc),
            contentType: "application/json"
        });
        promise.then(function(data){
            console.log(data);
        }, function(error) {
            console.log(22);
        });
    }

    return{
        GuardarPrueba:GuardarPrueba,
        insertar:insertar
    }
})();