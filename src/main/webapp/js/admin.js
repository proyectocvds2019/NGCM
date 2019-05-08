

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

function mensajeCorrecto(msg){
	swal('¡Perfecto!', msg, 'success').then((value)=>{location.reload();});
    
}


