$(document).ready(function(){
	console.log("Helloo");
        $(".nav-item").on({
     mouseenter: function () {
       $(this).css({ "background-color": "lightgray", "border-radius": "4px" });

     },
      mouseleave: function () {
       $(this).css({ "background-color": "#198754", "margin": "0pc 0pc" });

     },

   });
});