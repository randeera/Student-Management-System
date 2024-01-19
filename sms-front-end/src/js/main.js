function saveStudent() {
    let name = $("#name-input").val();
    let address = $("#address-input").val();
    let mobile = $("#mobile-input").val();
    
    $.ajax({
        method : "POST",
        contentType:"application/json",
        async : true,
        url : "http://localhost:8080/api/v1/students",
        data : JSON.stringify({
            "studentId": "",
            "studentName": name,
            "studentAddress": address,
            "mobileNumber": mobile
        }),
        success : function (data){
            alert("saved");
        },
        error : function (xhr, exception){
            alert('Error !');
        }
    });
}

function updateStudent() {
    let id = $("#id-input").val();
    let name = $("#name-input").val();
    let address = $("#address-input").val();
    let mobile = $("#mobile-input").val();

    $.ajax({
        method : "PUT",
        contentType:"application/json",
        async : true,
        url : "http://localhost:8080/api/v1/students",
        data : JSON.stringify({
            "studentId": id,
            "studentName": name,
            "studentAddress": address,
            "mobileNumber": mobile
        }),
        success : function (data){
            alert("updated");
        },
        error : function (xhr, exception){
            alert('Error !');
        }
    });
}

function deleteStudent() {
    let id = $("#id-input").val();
    $.ajax({
        method : "DELETE",
        async : true,
        url : "http://localhost:8080/api/v1/students" + id,
        success : function (data){
            alert("updated");
        },
        error : function (xhr, exception){
            alert('Error !');
        }
    });
}

