$( document ).ready(function() {
    console.log( "ready!" );
    console.log("welcome!")
   

    $(".navbar-nav").find("li").hover(
      function(){
        $(this).find("a").css("color","#dc3563");
      },
      function(){
        $(this).find("a").css("color","black");
      }
      );


$(".navbar-nav").find("li").click(function(){
  $(this).parent().find("a").removeClass("navclicked");
  $(this).find("a").addClass("navclicked");

  $(this).parent().find("li").removeClass("liclicked");
  $(this).addClass("liclicked");

});


});
