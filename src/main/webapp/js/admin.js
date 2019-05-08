

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


function comprobar(data){
    console.log(data);
    if(data.status != 'success'){
        $("#modalCargando").modal("show");
    }else{
        $("#modalCargando").modal("hide");
    }

}

function mensajeError(error){
    swal('¡Error!', error, 'error');
}

function swalError(){
	swal('¡Error!', "no se puede realizar la solicitud", 'error');
}

function mensajeCorrecto(msg){
	swal('¡Perfecto!', msg, 'success').then((value)=>{location.reload();});
    
}
function finalizar(data){
	location.reload();
}


