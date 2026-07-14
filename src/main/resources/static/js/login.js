// ==========================
// SHOW / HIDE PASSWORD
// ==========================

const password = document.getElementById("password");
const showPassword = document.getElementById("showPassword");

showPassword.addEventListener("click", () => {

    if (password.type === "password") {

        password.type = "text";
        showPassword.innerHTML = '<i class="bi bi-eye-slash-fill"></i>';

    } else {

        password.type = "password";
        showPassword.innerHTML = '<i class="bi bi-eye-fill"></i>';

    }

});

// ==========================
// DARK / LIGHT MODE
// ==========================

const themeButton = document.getElementById("themeToggle");

if(localStorage.getItem("theme") === "dark"){

    document.body.classList.add("dark");
    themeButton.innerHTML='<i class="bi bi-sun-fill"></i>';

}

themeButton.addEventListener("click",()=>{

    document.body.classList.toggle("dark");

    if(document.body.classList.contains("dark")){

        localStorage.setItem("theme","dark");
        themeButton.innerHTML='<i class="bi bi-sun-fill"></i>';

    }else{

        localStorage.setItem("theme","light");
        themeButton.innerHTML='<i class="bi bi-moon-stars-fill"></i>';

    }

});

// ==========================
// LOGIN BUTTON LOADING
// ==========================

const form=document.querySelector("form");
const loginButton=document.querySelector(".login-btn");

form.addEventListener("submit",function(){

    loginButton.disabled=true;

    loginButton.innerHTML=`
        <span class="spinner-border spinner-border-sm me-2"></span>
        Authenticating...
    `;

});

// ==========================
// INPUT ANIMATION
// ==========================

const inputs=document.querySelectorAll(".form-control");

inputs.forEach(input=>{

    input.addEventListener("focus",()=>{

        input.parentElement.style.transform="scale(1.02)";
        input.parentElement.style.transition=".3s";

    });

    input.addEventListener("blur",()=>{

        input.parentElement.style.transform="scale(1)";

    });

});

// ==========================
// BUTTON RIPPLE EFFECT
// ==========================

document.querySelectorAll(".btn").forEach(button=>{

    button.addEventListener("click",function(e){

        const circle=document.createElement("span");

        const diameter=Math.max(this.clientWidth,this.clientHeight);

        circle.style.width=diameter+"px";
        circle.style.height=diameter+"px";

        circle.style.left=e.offsetX-diameter/2+"px";
        circle.style.top=e.offsetY-diameter/2+"px";

        circle.classList.add("ripple");

        const ripple=this.getElementsByClassName("ripple")[0];

        if(ripple){

            ripple.remove();

        }

        this.appendChild(circle);

    });

});

// ==========================
// FLOATING CARD EFFECT
// ==========================

const card=document.querySelector(".login-card");

document.addEventListener("mousemove",(e)=>{

    let x=(window.innerWidth/2-e.pageX)/40;

    let y=(window.innerHeight/2-e.pageY)/40;

    card.style.transform=`rotateY(${x}deg) rotateX(${-y}deg)`;

});

document.addEventListener("mouseleave",()=>{

    card.style.transform="rotateY(0deg) rotateX(0deg)";

});

// ==========================
// PAGE LOAD ANIMATION
// ==========================

window.addEventListener("load",()=>{

    document.body.style.opacity="1";

});

// ==========================
// ENTER KEY SUPPORT
// ==========================

document.addEventListener("keydown",(e)=>{

    if(e.key==="Enter"){

        form.requestSubmit();

    }

});

// ==========================
// DEMO ACCOUNT AUTO-FILL
// ==========================

document.querySelectorAll("tbody tr").forEach(row=>{

    row.style.cursor="pointer";

    row.addEventListener("click",()=>{

        const username=row.children[1].innerText;
        const passwordText=row.children[2].innerText;

        document.querySelector("input[name='username']").value=username;
        document.querySelector("input[name='password']").value=passwordText;

        document.querySelector("input[name='username']").focus();

    });

});