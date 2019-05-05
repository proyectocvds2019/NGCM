

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

// $(".botonNovedadElemento").click(function(){
// 	$(".formModals").css("display","block");
// });

// $(".cerrarModal").click(function(){
// 	$(".formModals").css("display","none");
// });


