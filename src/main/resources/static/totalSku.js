function getTotalStockManagementData() {
    var xhttp = new XMLHttpRequest();
    xhttp.onreadystatechange = function () {
        if (this.readyState == 4 && this.status == 200) {
            console.log(this.responseText);


            var result = JSON.parse(this.responseText);
            if(result.product.length==0){
            Toastify({
                            text: "Data Not Found !",
                            duration: 3000,
                            gravity: "top",
                            position: 'right',
                            backgroundColor: "red",
                            close: true
                        }).showToast();
            }

            else{

            for (var i = 0; i < result.product.length; i++) {

                document.getElementById("todayStockTable").innerHTML +=
                    '<tr class="name1">' +
                    '<td>' + (i + 1) + '</td>' +
                    '<td class="name">' + result.product[i].name_of_item + '</td>' +
                    '<td class="name">' + result.product[i].num_pcs + '</td>' +
                    '<td class="name">' + result.product[i].per_pcs_weight + '</td>' +
                    '<td class="name">' + result.product[i].packaging + '</td>' +
                    '<td class="name">' + result.product[i].carton_gross_weight + '</td>' +
                    '<td class="name">' + result.product[i].hsn + '</td>' +
                    '<td ><span class="btn_edit" ><a href="#" class="btn btn-link ">Edit</a> </span>' +
                    '<span class="btn_save"> <a href="#" class="btn btn-link" > Save</a> | </span>' +
                    '<span class="btn_cancel"> <a href="#" class="btn btn-link"> Cancel</a> | </span></td>' +
                    '</tr> ';

            }

             Toastify({
                                                        text: "Data Fetched Successfully",
                                                        duration: 3000,
                                                        gravity: "top",
                                                        position: 'right',
                                                        backgroundColor: "#01cf68",
                                                        close: true
                                                    }).showToast();

                                                    hide();
        }

        }
    };
    xhttp.open("GET", "/api/getAllData?user_name="+localStorage.getItem("user_name"), true);
    xhttp.send();

}
function hide(){

	$("#todayStockTable").find('.btn_save').hide();
	$('#todayStockTable').find('.btn_cancel').hide();
}



var oldSku=[];
$("#todayStockTable").on('click', '.btn_edit', function(event)
{
	event.preventDefault();
	var tbl_row = $(this).closest('tr');

	var row_id = tbl_row.attr('name');
   // console.log(tbl_row.find('.name')[0].innerHTML);
    oldSku[0]=tbl_row.find('.name')[0].innerHTML;
	tbl_row.find('.btn_save').show();
	tbl_row.find('.btn_cancel').show();

	//hide edit button
	tbl_row.find('.btn_edit').hide();

	//make the whole row editable
	tbl_row.find('.name')
	.attr('contenteditable', 'true')
	.attr('edit_type', 'button')
	.addClass('bg-warning')
	.css('padding','3px')



});



$("#todayStockTable").on('click', '.btn_cancel', function(event)
	{
		window.location.reload();
	});
	//--->button > cancel > end


	//--->save whole row entery > start
	$("#todayStockTable").on('click', '.btn_save', function(event)
	{
		event.preventDefault();
		var tbl_row = $(this).closest('tr');

		var row_id = tbl_row.attr('name');


		//hide save and cacel buttons
		tbl_row.find('.btn_save').hide();
		tbl_row.find('.btn_cancel').hide();

		//show edit button
		tbl_row.find('.btn_edit').show();


		//make the whole row editable
		tbl_row.find('.name')
		.attr('edit_type', 'click')
		.removeClass('bg-warning')
		.css('padding','')

		//--->get row data > start
		var arr = {
		"name_of_item":tbl_row.find('.name')[0].innerHTML,
		"num_pcs":tbl_row.find('.name')[1].innerHTML,
		"per_pcs_weight":tbl_row.find('.name')[2].innerHTML,
		"packaging":tbl_row.find('.name')[3].innerHTML,
		"carton_gross_weight":tbl_row.find('.name')[4].innerHTML,
		"hsn":tbl_row.find('.name')[5].innerHTML,
		"user_name":localStorage.getItem("user_name")
		};
		console.log(arr);

		var xhttp = new XMLHttpRequest();
                    xhttp.onreadystatechange = function() {
                        if (this.readyState == 4 && this.status == 200) {
                            console.log(this.responseText);
                            if(this.responseText == "Update"  ){
                                Toastify({
                                    text: "Sku List Is Updated Successfully ",
                                    duration: 3000,
                                    gravity: "top",
                                    position: 'center',
                                    backgroundColor: "#01cf68",
                                    close : true
                                  }).showToast();
                               setTimeout(function(){
                                window.location.reload();
                                }, 3000);

                              }
                              else if(this.responseText == "Already"){
                               Toastify({
                                                                 text: "Name Of Item Already Exist !! ",
                                                                 duration: 3000,
                                                                 gravity: "top",
                                                                 position: 'center',
                                                                 backgroundColor: "#ff0000",
                                                                 close : true
                                                              }).showToast();
                                                               setTimeout(function(){
                                                                         window.location.reload();
                                                                         }, 3000);
                              }
                              else{
                                Toastify({
                                   text: "Same File !! ",
                                   duration: 3000,
                                   gravity: "top",
                                   position: 'center',
                                   backgroundColor: "#ff0000",
                                   close : true
                                }).showToast();
                              }
                         }
                    };

                    xhttp.open("POST","/api/updateSku?nameOfItem="+oldSku[0], true);
                    xhttp.setRequestHeader("Content-type", "application/json");
                    xhttp.send(JSON.stringify(arr));


	});




function openPopup(){
document.getElementById("myModal").style.display = "block";

}

function closePopup(){
document.getElementById("myModal").style.display = "none";

}




  function addSku(){
    var xhttp = new XMLHttpRequest();
            xhttp.onreadystatechange = function() {
                if (this.readyState == 4 && this.status == 200) {
                    console.log(this.responseText);
                    if(this.responseText == "Save"  ){
                        Toastify({
                            text: "Sku List Is Added Successfully ",
                            duration: 3000,
                            gravity: "top",
                            position: 'center',
                            backgroundColor: "#01cf68",
                            close : true
                          }).showToast();
                       setTimeout(function(){
                        window.location.reload();
                        }, 3000);

                      }
                       else if(this.responseText == "Already"){
                             Toastify({
                                        text: "Name Of Item Already Exist !! ",
                                        duration: 3000,
                                        gravity: "top",
                                         position: 'center',
                                        backgroundColor: "#ff0000",
                                        close : true
                                        }).showToast();
                                         setTimeout(function(){
                                         window.location.reload();
                                         }, 3000);
                                                    }
                      else{
                        Toastify({
                           text: "Server ERROR !! ",
                           duration: 3000,
                           gravity: "top",
                           position: 'center',
                           backgroundColor: "#ff0000",
                           close : true
                        }).showToast();

                      }
                 }
            };
            var name_of_item=document.getElementById("name_of_item").value;
            var num_of_pcs=document.getElementById("num_of_pcs").value;
            var pcs_per_weight=document.getElementById("pcs_per_weight").value;
            var packaging=document.getElementById("packaging").value;
            var carton_gross_weight=document.getElementById("carton_gross_weight").value;
            var hsn=document.getElementById("hsn").value;
            if(name_of_item==""){
                        Toastify({
                           text: "Please Enter Name Of Item",
                           duration: 3000,
                           gravity: "top",
                           position: 'center',
                           backgroundColor: "#ff0000",
                           close : true
                        }).showToast();
            }

      else if(num_of_pcs==""){
                        Toastify({
                           text: "Please Enter Number Of Pcs",
                           duration: 3000,
                           gravity: "top",
                           position: 'center',
                           backgroundColor: "#ff0000",
                           close : true
                        }).showToast();
            }

      else if(pcs_per_weight==""){
                        Toastify({
                           text: "Please Enter Pcs Per Weight",
                           duration: 3000,
                           gravity: "top",
                           position: 'center',
                           backgroundColor: "#ff0000",
                           close : true
                        }).showToast();
            }

      else if(packaging==""){
                        Toastify({
                           text: "Please Enter Packaging",
                           duration: 3000,
                           gravity: "top",
                           position: 'center',
                           backgroundColor: "#ff0000",
                           close : true
                        }).showToast();
            }

      else if(carton_gross_weight==""){
                        Toastify({
                           text: "Please Enter Carton Gross Weight",
                           duration: 3000,
                           gravity: "top",
                           position: 'center',
                           backgroundColor: "#ff0000",
                           close : true
                        }).showToast();
            }

      else if(hsn==""){
                        Toastify({
                           text: "Please Enter Hsn",
                           duration: 3000,
                           gravity: "top",
                           position: 'center',
                           backgroundColor: "#ff0000",
                           close : true
                        }).showToast();
            }

            else{
            var temp={"name_of_item":name_of_item,
            "num_pcs":num_of_pcs,
            "per_pcs_weight":pcs_per_weight,
            "carton_gross_weight":carton_gross_weight,
            "packaging":packaging,
            "hsn":hsn,
            "user_name":localStorage.getItem("user_name")};
            xhttp.open("POST","/api/addSku", true);
            xhttp.setRequestHeader("Content-type", "application/json");
            xhttp.send(JSON.stringify(temp));
            }
            }