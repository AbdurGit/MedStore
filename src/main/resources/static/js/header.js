$(document).ready(function () {
  console.log("ready!");
  console.log("welcome!")


  $(".navbar-nav").find("li").hover(
    function () {
      $(this).find("a").css("color", "#dc3563");
    },
    function () {
      $(this).find("a").css("color", "black");
    }
  );


  $(".navbar-nav").find("li").click(function () {
    $(this).parent().find("a").removeClass("navclicked");
    $(this).find("a").addClass("navclicked");

    $(this).parent().find("li").removeClass("liclicked");
    $(this).addClass("liclicked");

  });

  $(function() {
  $("#notificationIcon").click(function () {

    var elementclass = $("#notification").attr("class");

    if (elementclass.includes("hide-notification")) {

      $("#notification").removeClass("hide-notification");
      reloadNotifications();


    } else {

      $("#notification").addClass("hide-notification");
    }



  });
  $(document).on("click", function(e) {
    // console.log(e.target);
    if ($(e.target).is("#notification,#notificationIcon,.bi-bell,.close-btn,.open-btn,.bi-x-lg") === false) {
      
      $("#notification").addClass("hide-notification");
    }
  });

})


  
    
    

  function reloadNotifications() {
    $.ajax({
      type: "GET",
      url: "/allNotification",
      success: function (data) {
        $("#notifContainer").html("");
        console.log(data);
        console.log(data.length)
        var notificationHtml = "";
var count=0;
        if (data.length > 0) {
          for (var i = 0; i < data.length; i++) {
            var notif = data[i];
            if (notif.muted == true) {
              continue;
            }
            count++;
            var expiringInDays = '';
            if (notif.expiringIn < 0) {
              if (notif.expiringIn == -1) {
                expiringInDays = "Expired " + Math.abs(notif.expiringIn) + " day ago";
              } else {
                expiringInDays = "Expired " + Math.abs(notif.expiringIn) + " days ago";
              }

            } else if (notif.expiringIn == 0) {
              expiringInDays = "Expiring today";
            } else if (notif.expiringIn == 1) {

              expiringInDays = "Expiring in " + notif.expiringIn + " day";
            } else {
              expiringInDays = "Expiring in " + notif.expiringIn + " days";
            }
            var singleNotification = " <div class=\"row  p-1 mb-2  single-notif\" ><div class=\"col-8\" ><div class=\"n-med-name\">" + notif.pdtName + "</div>" +
              "<div class=\"n-batch\">" + notif.batch + "</div> <div class=\"n-expiry\" >" + expiringInDays + "</div>" +
              "</div> <div class=\"col-4 align-self-center justify-content-center\">" +
              "<a href=\"javascript:void(0)\" class=\"p-1 open-btn\" data-id=\"" + notif.id + "\" style=\"display:inline;\"><i class=\"bi bi-arrow-right-square\"></i></a>" +
              "<a href=\"javascript:void(0)\" class=\"p-1 close-btn\" data-id=\"" + notif.id + "\" style=\"display:inline;\"><i class=\"bi bi-x-lg\"></i></a>" +
              "</div></div>";
            notificationHtml += singleNotification;
          }
          $("#notifContainer").html(notificationHtml);
        } else {
          $("#notifContainer").html("<div style=\" text-align:center;\">No Notification</div>");
        }

        if(count==0){
          $("#notifContainer").html("<div style=\" text-align:center;\">No Notification</div>");
        }


      },
      error: function (xhr, status, error) {

        alert("Exception occured!");
        console.log(xhr.responseText);
      }
    });
  }

  $("body").on("click", ".open-btn", function () {
    var notifId= $(this).attr("data-id");
    console.log(notifId);
    var path = window.location.pathname;
    var page = path.split("/").pop();
    if(page=="pdtListPage"){    
        var pdtId = $(this).attr("data-id");
        $.ajax({
          type: "GET",
          url: "/getProductById/" + pdtId,
          success: function (data) {
            $("#notification").addClass("hide-notification");
            $("#pdtViewContainer").show("fast");
            $('#pdtTable').hide("fast");
            $('#pdtTable_wrapper').hide("fast");
            console.log(data);
            document.getElementById("pdtImg").src = "images/" + data.productImagePath;
            $("#pdtNameVal").text("");
            $("#descVal").text("");
            $("#batchVal").text("");
            $("#priceVal").text("");
            $("#sellerVal").text("");
            $("#discountVal").text("");
            $("#typeVal").text("");
            $("#boxBoughtVal").text("");
            $("#boxSoldVal").text("");
            $("#noteVal").text("");
            $("#mfgDtVal").text("");
            $("#expiryDtVal").text("");
            $("#bonusVal").text("");
            $("#compositionVal").text("");
            $("#brandVal").text("");
            $("#rateVal").text("");
            $("#pdtNameVal").text(data.productName);
            $("#descVal").text(data.productDesc);
            $("#batchVal").text(data.batch);
            $("#priceVal").text(data.mrp);
            $("#sellerVal").text(data.sellerName);
            $("#discountVal").text(data.percentageDiscount);
            if(data.medType=="-1"){
              $("#typeVal").text("");
            }else{
              $("#typeVal").text(data.medType);
            }
             $("#boxBoughtVal").text(data.noBoxBought);
            $("#boxSoldVal").text(data.noBoxSold);
            $("#noteVal").text(data.additionalNotes);
            $("#mfgDtVal").text(formatDate(data.mfgDate));
            $("#expiryDtVal").text(formatDate(data.expDate));
            $("#bonusVal").text(data.bonus);
            $("#compositionVal").text(data.composition);
            $("#brandVal").text(data.brand);
            $("#rateVal").text(data.rate);
            $("#updatePdtBtn").attr("data-id","");
            $("#updatePdtBtn").attr("data-id",pdtId);
            $("#deletePdtBtn").attr("data-id","");
            $("#deletePdtBtn").attr("data-id",pdtId);
          },
          error: function (xhr, status, error) {
            alert("Exception occured!");
            console.log(xhr.responseText);
          }
        });
    }else if(page=="medstore"){
        var pdtId = $(this).attr("data-id");
        $.ajax({
          type: "GET",
          url: "/getProductById/" + pdtId,
          success: function (data) {
            $("#notification").addClass("hide-notification");
            $("#pdtViewContainer").show("fast");
            $("#cardContainer").hide("fast");
            console.log(data);
            document.getElementById("pdtImg").src = "images/" + data.productImagePath;
            $("#pdtNameVal").text("");
            $("#descVal").text("");
            $("#batchVal").text("");
            $("#priceVal").text("");
            $("#sellerVal").text("");
            $("#discountVal").text("");
            $("#typeVal").text("");
            $("#boxBoughtVal").text("");
            $("#boxSoldVal").text("");
            $("#noteVal").text("");
            $("#mfgDtVal").text("");
            $("#expiryDtVal").text("");
            $("#bonusVal").text("");
            $("#compositionVal").text("");
            $("#brandVal").text("");
            $("#rateVal").text("");

            $("#pdtNameVal").text(data.productName);
            $("#descVal").text(data.productDesc);
            $("#batchVal").text(data.batch);
            $("#priceVal").text(data.mrp);
            $("#sellerVal").text(data.sellerName);
            $("#discountVal").text(data.percentageDiscount);
            if (data.medType == "-1") {
              $("#typeVal").text("");
            } else {
              $("#typeVal").text(data.medType);
            }

            $("#boxBoughtVal").text(data.noBoxBought);
            $("#boxSoldVal").text(data.noBoxSold);
            $("#noteVal").text(data.additionalNotes);
            $("#mfgDtVal").text(formatDate(data.mfgDate));
            $("#expiryDtVal").text(formatDate(data.expDate));
            $("#bonusVal").text(data.bonus);
            $("#compositionVal").text(data.composition);
            $("#brandVal").text(data.brand);
            $("#rateVal").text(data.rate);
            $("#updatePdtBtn").attr("data-id", "");
            $("#updatePdtBtn").attr("data-id", pdtId);
            $("#deletePdtBtn").attr("data-id", "");
            $("#deletePdtBtn").attr("data-id", pdtId);
          },
          error: function (xhr, status, error) {

            alert("Exception occured!");
            console.log(xhr.responseText);
          }
        });
     


    }else{
      alert("Please go to Home/Products page to update.")

    }
    console.log( page );
   
   })

  $("body").on("click", ".close-btn", function () {
    var notifId = $(this).attr("data-id");
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
      url: "/muteNotification/" + notifId,
      // data:{
      //   notificationId:notifId
      // },
      success: function (data) {
        
        reloadNotifications();
      },
      error: function (xhr, status, error) {

        alert("Exception occured!");
        console.log(xhr.responseText);
      }
    })


  });
  function formatDate(date) {
    return moment(date).format("DD-MM-YYYY");
  }

// validation for input of rate.
// By default 28.56 % discount is applied to mrp to calculate rate.
// Later user can change the rate.

  $("#pdtMrpInput").on('input', function(){
    var inputmrp=$(this).val();
    var rate=inputmrp*(1-.2856);
    rate = +rate.toFixed(2);
    if(!isNaN(rate)){
      $("#pdtRateInput").val("");
      $("#pdtRateInput").val(rate);
    }else{
      $("#pdtRateInput").val("");  
    }
    });



});