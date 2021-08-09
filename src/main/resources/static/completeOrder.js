var client = new Paho.MQTT.Client("18.118.36.18", Number(9001), "clientId");

//set callback handlers
client.onConnectionLost = onConnectionLost;
client.onMessageArrived = onMessageArrived;

//connect the client
client.connect({onSuccess:onConnect});
                   function onConnect() {
                    //Once a connection has been made, make a subscription and send a message.
                    console.log("onConnect");
                    client.subscribe("complete");
                    }
function onConnectionLost(responseObject) {
   if (responseObject.errorCode !== 0) {
   console.log("onConnectionLost:"+responseObject.errorMessage);
   }
   }

function onMessageArrived(message) {
   console.log(message.payloadString)
   }
function getSalesData( ){
    var xhttp = new XMLHttpRequest();
       xhttp.onreadystatechange = function () {
           if (this.readyState == 4 && this.status == 200) {
               console.log(this.responseText);


               var result = JSON.parse(this.responseText);
              document.getElementById("todayStockTable").innerHTML="";
    if(result.salesNo.length==0){
    Toastify({
                           text: "Data Not Found !",
                           duration: 3000,
                           gravity: "top",
                           position: 'right',
                           backgroundColor: "Red",
                           close: true
                       }).showToast();


    }else{

               Toastify({
                   text: "Data Fetched Successfully",
                   duration: 3000,
                   gravity: "top",
                   position: 'right',
                   backgroundColor: "#01cf68",
                   close: true
               }).showToast();


               for (var i = 0; i < result.salesNo.length; i++) {
                   document.getElementById("todayStockTable").innerHTML +=
                       "<tr>" +
                       '<td class="text-center" style="width: 7%;">' + (i + 1) + '</td>' +
                       '<td class="text-center" style="width: 25%;">' + result.salesNo[i].sales_no + '</td>' +
                       '<td class="text-center" style="width: 15%;"> <input type="submit" onclick="viewOrder(this)" value="View" class="myBtn bg-theme text-light"> </td>' +
                       '<td class="text-center" style="width: 15%;"> <input type="submit" onclick="confirmed(this)" value="Complete" class="myBtn bg-theme text-light"> </td>' +

                       '</tr>';
               }}
           }
       };
       xhttp.open("GET", "api/getSalesNoWithUser?user_name="+localStorage.getItem("user_name"), true);
       xhttp.send();

   }
function confirmed(element) {
    if (confirm("Are you sure you want to complete this Sales No?")) {
    editQuantity(element);
    }
    else {
    location.reload();
    }
    }

  function editQuantity(element){
  var ed=element.parentNode.parentNode;
   var sales=ed.getElementsByTagName("td")[1].innerHTML;
   var XHR2 = new XMLHttpRequest();
      XHR2.open("POST", "/api/updateSalesNo1?sales_no="+sales+"&user_name="+localStorage.getItem("user_name"));
      XHR2.onload = function() {
             console.log(XHR2.responseText);
             var response = JSON.parse(XHR2.responseText);

             if(response.message=="Successful") {
                alert("Sales No Updated Successfully");
                 var  message = new Paho.MQTT.Message(sales);
                                    message.destinationName = "complete";
                                    client.send(message);
                getSalesData();
             }
           else {
            location.reload();
             }
         }
   XHR2.send();

}


function viewOrder(element) {
 var ed=element.parentNode.parentNode;
   var sales=ed.getElementsByTagName("td")[1].innerHTML;
    localStorage.setItem("sales_no",sales);
    window.location.href="viewSn";

    }
