function tableList( date){
    var xhttp = new XMLHttpRequest();
       xhttp.onreadystatechange = function () {
           if (this.readyState == 4 && this.status == 200) {
               console.log(this.responseText);


               var result = JSON.parse(this.responseText);
              document.getElementById("productTable").innerHTML="";
    if(result.todayIn.length==0){
    Toastify({
                           text: "No Data",
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

               for (var i = 0; i < result.todayIn.length; i++) {
                   document.getElementById("productTable").innerHTML +=
                       "<tr>" +
                       '<td class="text-center" style="width: 7%;">' + (i + 1) + '</td>' +
                       '<td class="text-center" style="width: 25%;">' + result.todayIn[i].name_of_item + '</td>' +
                       '<td class="text-center" style="width: 15%;">' + result.todayIn[i].no_of_pcs + '</td>' +
                       '<td class="text-center" style="width: 15%;">' + result.todayIn[i].per_pcs_weight + '</td>' +
                       '<td class="text-center" style="width: 10%;">' + result.todayIn[i].packaging + '</td>' +
                       '<td class="text-center" style="width: 10%;">' + result.todayIn[i].carton_gross_weight + '</td>' +
                       '<td class="text-center" style="width: 10%;">' + result.todayIn[i].hsn + '</td>' +
                       '</tr>';
               }}
           }
       };
       xhttp.open("GET", "api/getTodayInNameOfItem?date="+date, true);
       xhttp.send();

   }


   function callWithCurrentDate(){
   var date=new Date();
   var year=date.getFullYear();
   var month=date.getMonth()+1;
   var day=date.getDate();
   month=checkTime(month);
   day=checkTime(day);
   document.getElementById("date").value=year+"-"+month+"-"+day;
   console.log(document.getElementById("date").value);
      tableList(document.getElementById("date").value) ;
    }

   function checkTime(i) {
     if (i < 10) {
       i = "0" + i;
     }
     return i;
   }

   window.onload=callWithCurrentDate();
   function callWithDate(){
   tableList(document.getElementById("date").value) ;
   }