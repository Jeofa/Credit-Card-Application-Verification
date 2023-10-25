
    const prevBtns = document.querySelectorAll(".btn-prev");
    const nextBtns = document.querySelectorAll("#apply-btn");
    const progress = document.getElementById("myprogress");
    const formSteps = document.querySelectorAll(".form-step");
    const progressSteps = document.querySelectorAll(".myprogress-step");


    let formStepsNum = 0;

    nextBtns.forEach((btn) => {
    btn.addEventListener("click", () => {
        formStepsNum++;
        // updateFormSteps();
        updateProgressbar();

    });
    });

    function updateProgressbar() {
        progressSteps.forEach((progressStep, idx) => {
            if (idx < formStepsNum + 1) {
            progressStep.classList.add("myprogress-step-active");
            } else {
            progressStep.classList.remove("myprogress-step-active");
            }
        });
    }

    const progressActive = document.querySelectorAll(".myprogress-step-active");

    progress.style.width =  ((progressActive.length - 1) / (progressSteps.length - 1)) * 100 + "%";
    