getAllStudents();
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
            getAllStudents();
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
            getAllStudents();
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
            alert("Deleted");
            getAllStudents();
        },
        error : function (xhr, exception){
            alert('Error !');
        }
    });
}

function getAllStudents() {

    $.ajax({
        method : "GET",
        async : true,
        url : "http://localhost:8080/api/v1/students",
        success : function (data){
            if(data.code === "00") {
                $("#table-body").empty();
                for (const std of data.content) {
                    let stdId = std.studentId;
                    let stdName = std.studentName;
                    let stdAddress = std.studentAddress;
                    let stdMobile = std.mobileNumber;

                    let row = `<tr><td>${stdId}</td><td>${stdName}</td><td>${stdAddress}</td><td>${stdMobile}</td></tr>`;
                    $('#table-body').append(row);
                }
            }
        },
        error : function (xhr, exception){
            alert('Error !');
        }
    });
}

