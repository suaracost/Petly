document.addEventListener('DOMContentLoaded', function() {
  
  const carrusel = document.querySelector(".carruselServ");
  let carruselItems = document.querySelectorAll(".card");
  let carruselItemLast = carruselItems[carruselItems.length - 1];
  
  const btnLeft = document.querySelector("#btn-left-serv");
  const btnRight = document.querySelector("#btn-right-serv");
  
  carrusel.insertAdjacentElement("afterbegin", carruselItemLast);
  
  function Next() {
    let carruselItemFirst = document.querySelectorAll(".card")[0];
    carrusel.style.marginLeft = "-864px";
    carrusel.style.transition = "all 0.5s";
    setTimeout(function () {
      carrusel.style.transition = "none";
      carrusel.insertAdjacentElement("beforeend", carruselItemFirst);
      carrusel.style.marginLeft = "-432px";
    }, 500);
  }

  function Prev() {
    let carruselItem = document.querySelectorAll(".card");
    let carruselItemLast = carruselItem[carruselItem.length - 1];
    carrusel.style.marginLeft = "0px";
    carrusel.style.transition = "all 0.5s";
    setTimeout(function () {
      carrusel.style.transition = "none";
      carrusel.insertAdjacentElement("afterbegin", carruselItemLast);
      carrusel.style.marginLeft = "-432px";
    }, 500);
  }
  
  btnRight.addEventListener("click", function () {
    Next();
  })

  btnLeft.addEventListener("click", function () {
    Prev();
  })

  const carruselVet = document.querySelector(".carruselVet");
  let carruselItemsVet = document.querySelectorAll(".vetCard");
  let carruselItemLastVet = carruselItemsVet[carruselItemsVet.length - 1];

  const btnLeftVet = document.querySelector("#btn-left-vet");
  const btnRightVet = document.querySelector("#btn-right-vet");

  carruselVet.insertAdjacentElement("afterbegin", carruselItemLastVet);

  function NextVet() {
    let carruselItemFirstVet = document.querySelectorAll(".vetCard")[0];
    carruselVet.style.marginLeft = "-864px";
    carruselVet.style.transition = "all 0.5s";
    setTimeout(function () {
      carruselVet.style.transition = "none";
      carruselVet.insertAdjacentElement("beforeend", carruselItemFirstVet);
      carruselVet.style.marginLeft = "-432px";
    }, 500);
  }

  function PrevVet() {
    let carruselItemVet = document.querySelectorAll(".vetCard");
    let carruselItemLastVet = carruselItemVet[carruselItemVet.length - 1];
    carruselVet.style.marginLeft = "0px";
    carruselVet.style.transition = "all 0.5s";
    setTimeout(function () {
      carruselVet.style.transition = "none";
      carruselVet.insertAdjacentElement("afterbegin", carruselItemLastVet);
      carruselVet.style.marginLeft = "-432px";
    }, 500);
  }

  btnRightVet.addEventListener("click", function () {
    NextVet();
  })

  btnLeftVet.addEventListener("click", function () {
    PrevVet();
  })

});
