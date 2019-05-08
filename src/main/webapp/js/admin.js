
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
    if(data.status == 'success'){
    	location.reload();
    }

}

function mensajeError(error){
    swal('¡Error!', error, 'error');
    errorRegistrarElemento = true;
}

function swalError(){
	swal('¡Error!', "no se puede realizar la solicitud", 'error');
}

function mensajeCorrecto(msg){
	if(!errorRegistrarElemento){
		swal('¡Perfecto!', ''+msg+'!', 'success').then((value)=>{location.reload();});
	}
	errorRegistrarElemento = false;
    
}
function finalizar(data){
	location.reload();
}


