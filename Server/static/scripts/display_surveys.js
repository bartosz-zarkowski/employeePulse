        /* Get the text field */
        var linkButtons = document.getElementsByClassName("btn-copy-survey");

        for (let button of linkButtons) {
            button.addEventListener("click", event => {

                /* get input id */
                id = button.id.split('-')[button.id.split('-').length - 1];
                inputID = 'link-input-' + id;
                input = document.getElementById(inputID);
                /* Select the text field */

                input.select();
                input.setSelectionRange(0, 99999); /* For mobile devices */

                /* Copy the text inside the text field */
                navigator.clipboard.writeText(input.value);

                /* Alert the copied text */
                alert("Skopiowany link: " + input.value);
            })
        }