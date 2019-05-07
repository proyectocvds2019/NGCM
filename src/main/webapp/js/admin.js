

$(".botonMenu").click(function(){
	$(".botonMenu").removeClass("active");
	$(this).addClass("active");
	$(".collapse").collapse("hide");
});

$(".botonMenuConfiguracion").click(function(){
	$(".botonMenuConfiguracion").removeClass("active");
	$(this).addClass("active");
	$(".collapseConfiguracion").collapse("hide");
});


function comprobarRegistroElemento(){
    console.log("holaaaaaaaaaaaaaaaaaaaaaaa");

}

function mensajeError(error){
    Console.log("ñljfdñlksajfdds");
    swal('¡Error!', 'No se ha podido registrar el elemento!', 'error');
}

function mensajeCorrecto(msg){
    swal('¡Perfecto!', ''+msg+'!', 'success').then((value)=>{console.log(value);location.reload();});
}


