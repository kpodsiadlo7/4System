document.addEventListener("DOMContentLoaded", function() {
	
	var homeBtn = document.getElementById("homeButton");
	var spinner = document.getElementById("spinner-border");
	
	// home.html
	if (document.getElementById('HomePage')) {
		var uploadBtn = document.getElementById("uploadBtn");
	    var displayBtn = document.getElementById("displayBtn");
	
	    uploadBtn.addEventListener("click", function() {
	        window.location.href = "upload";
	    });
	
	    displayBtn.addEventListener("click", function() {
	        displayBtn.disabled = true;
	        spinner.classList.add("d-inline-block");
	        window.location.href = "display";
	    }); 
    }
    // end home.html
	
	// display.html
	if (document.getElementById('DisplayPage')) {
	    var btnBack = document.getElementById("btnBack");
		var btnNext = document.getElementById("btnNext");
		var spinnerBack = document.getElementById("spinnerBack");
		var spinnerNext = document.getElementById("spinnerNext");
		

		btnBack.addEventListener("click", function() {
			btnBack.disabled = true;
			spinnerBack.classList.add("d-inline-block");
		});
		
		btnNext.addEventListener("click", function() {
			spinnerNext.classList.add("d-inline-block");
	   	});
	    homeBtn.addEventListener("click", function() {
	    	window.location.href = "/4system";
		});
	}
    // end display.html
	
	
	// upload.html
	if (document.getElementById('UploadPage')) {
        var summaryBtn = document.getElementById("summaryBtn");
        
        homeBtn.addEventListener("click", function() {
        	window.location.href = "/4system";
    	});
        
        summaryBtn.addEventListener("click", function() {
        	summaryBtn.disabled = true;
        	spinner.classList.add("d-inline-block");
        	window.location.href = "display";
        });
	}
	// end upload.html
});

function disableButton() {
	var spinner = document.getElementById("spinner-border");
   	document.getElementById("submitButton").disabled = true;
   	document.getElementById("homeButton").disabled = true;
   	
   	document.querySelector('.form-control').style.display = 'none';
   	document.querySelector('.form-control').removeAttribute('onchange')
   	
    spinner.classList.add("d-inline-block");
    document.getElementById('selectFile').innerText = "Dodawanie użytkowników do bazy danych...";
}

function updateFile() {
    var fileInput = document.getElementById("formFile");
    var fileName = fileInput.value.split("\\").pop();
    
    var submitButton = document.getElementById("submitButton");
    var allowedExtensions = /(\.xml|\.json)$/i;

    if (!allowedExtensions.exec(fileName)) {
        document.getElementById('selectFile').innerText = "Zły format pliku!";
        document.getElementById('selectFile').style.color = 'red';
        submitButton.disabled = true;
        exit();
    } else {
        submitButton.disabled = false;
        document.getElementById('selectFile').innerText = "maks. 5mb";
        document.getElementById('selectFile').style.color = 'black';
    }
    
    var fileSizeLabel = document.getElementById("selectFile");
    if (fileInput.files.length > 0) {
        var fileSize = fileInput.files[0].size; 
        var maxSizeInBytes = 5 * 1024 * 1024;

        if (fileSize <= maxSizeInBytes) {
            fileSizeLabel.innerHTML = "maks. 5mb";
            document.getElementById('selectFile').style.color = 'black';
            submitButton.disabled = false;
        } else {
            fileSizeLabel.innerHTML = "Plik jest za duży (maks. 5mb)";
            document.getElementById('selectFile').style.color = 'red';
            submitButton.disabled = true;
        }
    }
}
