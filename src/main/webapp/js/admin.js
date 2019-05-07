
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
	console.log("sfdfasfsdfsdf");
    swal('¡Error!', error, 'error');
    errorRegistrarElemento = true;
}

function mensajeCorrecto(msg){
	if(!errorRegistrarElemento){
		swal('¡Perfecto!', ''+msg+'!', 'success').then((value)=>{location.reload();});
	}
	errorRegistrarElemento = false;
    
}


