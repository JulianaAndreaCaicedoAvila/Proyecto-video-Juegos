const Hogo = document.getElementById("Hogo");
const jugador = document.getElementById("jugador");
const jugadorSaltar = document.getElementById("jugadorSaltar");
const Murcielago = document.getElementById("Murcielago");
const fondo = document.getElementById("fondo");

document.addEventListener("DOMContentLoaded", function() {
    document.addEventListener("keydown", function(event) {
        if (event.key === "o" || event.key === "O") {
            Pausa();

            mostrarP();
        }
    });
});

document.addEventListener("DOMContentLoaded", function() {
    document.addEventListener("keydown", function(event) {
        if (event.key === "a" || event.key === "A") {
            mostrarCorrecto();
        }
    });
});


document.addEventListener("DOMContentLoaded", function() {
    document.addEventListener("keydown", function(event) {
        if (event.key === "s" || event.key === "S") {
            mostrarR();
        }
    });
});


document.addEventListener("DOMContentLoaded", function() {
    document.addEventListener("keydown", function(event) {
        if (event.key === "p" || event.key === "P") {
            mostrarCargaNiveles();
        }
    });
});



function mostrarP() {
    const contenedor = document.getElementById("contenedor");

    const div1 = document.createElement("div");
    div1.setAttribute("id", "Carganivel");
    div1.setAttribute("style", "width: 40%; height: 25%; left: 30%; top: 10%; background: linear-gradient(135deg, rgba(207, 166, 222, 0.7), rgba(166, 95, 213, 0.7));");

    const h1_1 = document.createElement("h1");
    h1_1.textContent = "Perdiste";

    const center_1 = document.createElement("center");
    center_1.appendChild(h1_1);

    div1.appendChild(center_1);

    contenedor.appendChild(div1);
}


function mostrarCorrecto() {
    const contenedor = document.getElementById("contenedor");

    const div1 = document.createElement("div");
    div1.setAttribute("id", "Carganivel");
    div1.setAttribute("style", "width: 40%; height: 25%; left: 30%; top: 73%; background: linear-gradient(135deg, rgba(207, 166, 222, 0.7), rgba(166, 95, 213, 0.7));");

    const h1_1 = document.createElement("h1");
    h1_1.textContent = "Correcto";

    const center_1 = document.createElement("center");
    center_1.appendChild(h1_1);

    div1.appendChild(center_1);

    contenedor.appendChild(div1);
}


function mostrarCargaNiveles() {
    const contenedor = document.getElementById("contenedor");

    const div1 = document.createElement("div");
    div1.setAttribute("id", "Carganivel");
    div1.setAttribute("style", "width: 40%; height: 30%; left: 5%; top: 40%; background: linear-gradient(135deg, rgba(207, 166, 222, 0.7), rgba(166, 95, 213, 0.7));");

    const h1_1 = document.createElement("h1");
    h1_1.textContent = "3 x 5";

    const center_1 = document.createElement("center");
    center_1.appendChild(h1_1);

    div1.appendChild(center_1);

    contenedor.appendChild(div1);
}

function mostrarR() {
    const contenedor = document.getElementById("contenedor");

   
    const div1 = document.createElement("div");
    div1.setAttribute("id", "Carganivel");
    div1.setAttribute("style", "width: 40%; height: 30%; left: 55%; top: 40%; background: linear-gradient(135deg, rgba(166, 95, 213, 0.7), rgba(207, 166, 222, 0.7));");

    const h1_1 = document.createElement("h1");
    h1_1.textContent = "15";

    const center_1 = document.createElement("center");
    center_1.appendChild(h1_1);

    div1.appendChild(center_1);

    contenedor.appendChild(div1);
}



document.addEventListener("DOMContentLoaded", function() {
    const hogo = document.getElementById("Hogo");
    const jugador = document.getElementById("jugador");

    hogo.addEventListener("click", function() {
        alert("¡Se ha pulsado el obstáculo Hogo!");
    });

    jugador.addEventListener("click", function() {
        alert("¡Se ha pulsado el jugador!");
    });
});



document.addEventListener("DOMContentLoaded", function() {
    jugadorSaltar.addEventListener("click", function(){
        jugador.classList.add("jugadorSaltar");
        setTimeout(function(){
            jugador.classList.remove("jugadorSaltar");
        }, 1000); 
    });
});
    BotonPausa.addEventListener("click", ()=>{
        Pausa(); 
    })
    function Pausa(){
        Hogo.style.animationPlayState="paused";
        Murcielago.style.animationPlayState="paused";
        jugador.style.animationPlayState="paused";
        fondo.style.animationPlayState="paused";
    }
    BotonPlay.addEventListener("click", ()=>{
        Play(); 
    })
    function Play(){
        Hogo.style.animationPlayState="running";
        Murcielago.style.animationPlayState="running";
        jugador.style.animationPlayState="running";
        fondo.style.animationPlayState="running";
       
    }
BotonReanudar.addEventListener("click", ()=>{
    Pausa(); 
    Reanudar(); 
})
function Reanudar(){
    Hogo.classList.remove("Reinicio_Obstaculos");
    alert("Se va a reiniciar el juego .-.")
    Hogo.classList.add("Reinicio_Obstaculos");  
    Play();  
}

function Perder(jugador, obstaculo) {
  var Perder=false;
  if(obstaculo.x + obstaculo.width >= jugador.x && obstaculo.x < jugador.x + jugador.width){
    if(obstaculo.y + obstaculo.height >= jugador.y && obstaculo.y < jugador.y + jugador.height){
        Perder=true;
     }
  }

  if(obstaculo.x <= jugador.x && obstaculo.x + obstaculo.width >= jugador.x + jugador.width){
    if(obstaculo.y <= jugador.y && obstaculo.y + obstaculo.height >= jugador.y + jugador.height){
        Perder=true;
}
  }

  if(jugador.x <= obstaculo.x && jugador.x + jugador.width >= obstaculo.x + obstaculo.width){
    if(jugador.y<= obstaculo.y && jugador.y + jugador.height >= obstaculo.y + obstaculo.height){
        Perder=true;
}
  }
return Perder;
}
function VerificarPerder() {
    if (Perder(jugador, Hogo)) {
        console.log("¡Perdiste!");
        alert("Perdiste");
    } else {
        console.log("¡Sigue jugando!");
    }
}
VerificarPerder() ;

function irANiveles() {
    window.location.href = "Niveles.html";
}

function continuarNivel() {
    window.location.href = "Juego.html";
}

function Comentario() {
    window.location.href = "Comentarios.html";
}

function Home() {
    window.location.href = "Home.html";
}

function Registro() {
    window.location.href = "Registro.html";
}
function InciarSesion() {
    window.location.href = "IniciarSesion.html";
}

function Sumas() {
    window.location.href = "Sumas.html";
}
function Restas() {
    window.location.href = "Restas.html";
}
function Multiplicaciones() {
    window.location.href = "Multiplicaciones.html";
}
function Divisiones() {
    window.location.href = "Division.html";
}
function paginaIncio() {
    window.location.href = "PaginaDeInicio.html ";
}

/*/MENÚ NIVELES*/
function toggleMenu() {
    var menuList = document.getElementById("menuList");
    menuList.style.display = menuList.style.display === "block" ? "none" : "block";
}
