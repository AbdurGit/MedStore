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
$("#notificationIcon").click(function(){
  
  var elementclass=$("#notification").attr("class");
  
  if(elementclass.includes("hide-notification")){
   
    $("#notification").removeClass("hide-notification");
reloadNotifications();


  }else{
    
    $("#notification").addClass("hide-notification");
  }

  
  
})
function reloadNotifications(){
  $.ajax({
    type: "GET",
    url: "/allNotification",
    success: function (data) {
      $("#notifContainer").html("");
      console.log(data);
      console.log(data.length)
      var notificationHtml="";
  
      if(data.length>0){
        for(var i=0;i<data.length;i++){
          var notif=data[i];
          if(notif.muted==true){
            continue;
          }
          var expiringInDays='';
          if(notif.expiringIn<0){
            if(notif.expiringIn==-1){
              expiringInDays="Expired "+Math.abs(notif.expiringIn)+" day ago";
            }else{
              expiringInDays="Expired "+Math.abs(notif.expiringIn)+" days ago";
            }
            
          }else if(notif.expiringIn==0){
            expiringInDays="Expiring today";
          }else if (notif.expiringIn==1){
  
            expiringInDays="Expiring in "+notif.expiringIn+" day";
          }else{
            expiringInDays="Expiring in "+notif.expiringIn+" days";
          }
          var singleNotification=" <div class=\"row  p-1 mb-2  single-notif\" ><div class=\"col-8\" ><div class=\"n-med-name\">"+notif.pdtName+"</div>"
                                  +"<div class=\"n-batch\">"+notif.batch+"</div> <div class=\"n-expiry\" >"+expiringInDays+"</div>"
           + "</div> <div class=\"col-4 align-self-center justify-content-center\">"
                + "<a href=\"javascript:void(0)\" class=\"p-1 open-btn\" data-id=\""+notif.id+"\" style=\"display:inline;\"><i class=\"bi bi-arrow-right-square\"></i></a>"
                 +"<a href=\"javascript:void(0)\" class=\"p-1 close-btn\" data-id=\""+notif.id+"\" style=\"display:inline;\"><i class=\"bi bi-x-lg\"></i></a>"
                +"</div></div>";
                notificationHtml+=singleNotification;
        }
        $("#notifContainer").html(notificationHtml);
      }else{
        $("#notifContainer").html("<h4>No Notification</h4>");
      }
     
  
    },
    error: function (xhr, status, error) {
  
      alert("Exception occured!");
      console.log(xhr.responseText);
    }
  });
}


$("body").on("click",".close-btn",function(){
  var notifId=$(this).attr("data-id");
 console.log(notifId);

//   $.ajax({
//     type: "DELETE",
//     url: "/deleteNotification/"+notifId,
//     // data:{
//     //   notificationId:notifId
//     // },
//     success: function (data) {
//     alert("success!")
//     },
//     error: function (xhr, status, error) {

//       alert("Exception occured!");
//       console.log(xhr.responseText);
//     }
//   })
// })

$.ajax({
  type: "POST",
  url: "/muteNotification/"+notifId,
  // data:{
  //   notificationId:notifId
  // },
  success: function (data) {
  alert("success!")
  reloadNotifications();
  },
  error: function (xhr, status, error) {

    alert("Exception occured!");
    console.log(xhr.responseText);
  }
})


});
});

