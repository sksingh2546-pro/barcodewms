function getDashboardData() {
    var xhttp1 = new XMLHttpRequest();
    xhttp1.onreadystatechange = function() {
        if (this.readyState == 4 && this.status == 200) {
            // Typical action to be performed when the document is ready:
            var response = xhttp1.responseText;
            var result = JSON.parse(response);
            console.log(result);
            for(var i=0;i<result.data.length;i++){

               document.getElementById("todaySTCount").innerHTML=result.data[i].todayIn;
               document.getElementById("totalSTCount").innerHTML=result.data[i].totalIn;
                document.getElementById("todayPTCount").innerHTML=result.data[i].todayOut;
                document.getElementById("totalPTCount").innerHTML=result.data[i].TotalOut;

            }}
            }
            xhttp1.open("GET", "/api/getTotalInTotalOut", true);

                xhttp1.send();
            }

window.onload=getDashboardData();


function getExpireData() {
    var xhttp1 = new XMLHttpRequest();
    xhttp1.onreadystatechange = function() {
        if (this.readyState == 4 && this.status == 200) {
            // Typical action to be performed when the document is ready:
            var response = xhttp1.responseText;
            var result = JSON.parse(response);
            console.log(result);
            for(var key in result.expire){
              document.getElementById("orderTable").innerHTML+='<tr>'+
                                                              '<td class="text-center" >'+(parseInt(key)+1)+'</td>'+
                                                              '<td class="text-center" >'+result.expire[key].barcode+'</td>'+
                                                              '<td class="text-center" >'+result.expire[key].name_of_item+'</td>'+
                                                              '<td class="text-center" >'+result.expire[key].no_of_pcs+'</td>'+
                                                              '<td class="text-center" >'+result.expire[key].per_pcs_weight+'</td>'+
                                                              '<td class="text-center" >'+result.expire[key].packaging+'</td>'+
                                                              '<td class="text-center" >'+result.expire[key].carton_gross_weight+'</td>'+
                                                              '<td class="text-center" >'+result.expire[key].hsn+'</td>'+
                                                              '</tr>'

            }}
            }
            xhttp1.open("GET", "/api/getExpireList", true);

                xhttp1.send();
            }
window.onload=getExpireData();