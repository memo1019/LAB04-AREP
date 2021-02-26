Busqueda = (function () {

    function GuardarBusqueda(){
        var promise = $.get({
            url: "/Apps/Busqueda",
            contentType: "application/json"
        });
        promise.then(function(data){
            VistaBusqueda(data);
        }, function(error) {
            console.log(22);
        });
    }

    function VistaBusqueda(data){
        for (let i = 0; i < data.split("%").length; i++) {
            data.split("%")[i].split("#");
            $("#Busqueda").append("<li class='temporal'>"+data.split("%")[i].split("#")[0]+" "+data.split("%")[i].split("#")[1])
        }

    }



    function insertar(){
        var name = $("#Nombre").val()
        var num = $("#Edad").val()
        var promise = $.get({
            url: "/Apps/insertar",
            data: JSON.stringify(name+"!"+num),
            contentType: "application/json"
        });
        promise.then(function(data){
            console.log(data);
        }, function(error) {
            console.log(22);
        });
    }

    return{
        GuardarBusqueda:GuardarBusqueda,
        insertar:insertar
    }
})();