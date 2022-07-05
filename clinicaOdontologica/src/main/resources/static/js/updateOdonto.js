window.addEventListener('load', function () {


    //Buscamos y obtenemos el formulario donde estan
    //los datos que el usuario pudo haber modificado de la pelicula
    const formulario = document.querySelector('#update_odonto_form');

    formulario.addEventListener('submit', function (event) {
        let odontoId = document.querySelector('#odonto_id').value;

        //creamos un JSON que tendrá los datos de la película
        //a diferencia de una pelicula nueva en este caso enviamos el id
        //para poder identificarla y modificarla para no cargarla como nueva
        const formData = {
            id: document.querySelector('#odonto_id').value,
            nombre: document.querySelector('#nombre').value,
            apellido: document.querySelector('#apellido').value,
            matricula: document.querySelector('#matricula').value,

        };

        //invocamos utilizando la función fetch la API peliculas con el método PUT que modificará
        //la película que enviaremos en formato JSON
        const url = '/odontologos';
        const settings = {
            method: 'PUT',
            headers: {
                'Content-Type': 'application/json',
            },
            body: JSON.stringify(formData)
        }
          fetch(url,settings)
          .then(response => response.json())

    })

 })
 //Es la función que se invoca
        //cuando se hace clic sobre el ID de una película del listado

        //se encarga de llenar el formulario con los datos de la película

        //que se desea modificar

        function findBy(id) {

              const url = '/odontologos'+"/"+id;

              const settings = {

                  method: 'GET'

              }

              fetch(url,settings)

              .then(response => response.json())

              .then(data => {

                  let odontologo = data;

                  document.querySelector('#odonto_id').value = odontologo.id;

                  document.querySelector('#nombre').value = odontologo.nombre;

                  document.querySelector('#apellido').value = odontologo.apellido;

                  document.querySelector('#matricula').value = odontologo.matricula;

                  //el formulario por default está oculto y al editar se habilita

                  document.querySelector('#div_odonto_updating').style.display = "block";

              }).catch(error => {

                  alert("Error: " + error);

              })

          }