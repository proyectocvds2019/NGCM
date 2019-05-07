
var errorRegistrarElemento = false;

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


function comprobarRegistroElemento(data){
    console.log(data);

}

function mensajeError(error){
	console.log(error);
    swal('¡Error!', 'no se ha podido realizar la solicitud', 'error');
    errorRegistrarElemento = true;
}

function mensajeCorrecto(msg){
	if(!errorRegistrarElemento){
		swal('¡Perfecto!', 'Se ha procesado su solicitud!', 'success').then((value)=>{location.reload();});
	}
	errorRegistrarElemento = false;
    
}


