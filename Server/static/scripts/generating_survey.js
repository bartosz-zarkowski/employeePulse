// Default data and date

document.getElementById('defaultFill').addEventListener('click', (event) => {

    const now = new Date();
    now.setMinutes(now.getMinutes() - now.getTimezoneOffset());
    document.getElementById('startTime').value = now.toISOString().slice(0, 16);
    var future = now
    future.setMinutes(future.getMinutes() + 30);
    document.getElementById('endTime').value = future.toISOString().slice(0, 16);

    var title = document.getElementById('title');
    title.value = "Nastroje wśród pracowników";

    var question = document.getElementById('question');
    question.value = "Jak się dziś czujesz?";

    var answer1 = document.getElementById('answer1');
    answer1.value = "Bardzo dobrze";
    document.getElementById('emoji-1').setAttribute('value', '😃');

    var answer2 = document.getElementById('answer2');
    answer2.value = "Zwyczajnie";
    document.getElementById('emoji-2').setAttribute('value', '😐');

    var answer3 = document.getElementById('answer3');
    answer3.value = "Smutno mi";
    document.getElementById('emoji-3').setAttribute('value', '😢');

    var answer4 = document.getElementById('answer4');
    answer4.value = "Jestem zły";
    document.getElementById('emoji-4').setAttribute('value', '🤬');

    const defaultEmojis = ['😃', '😐', '😢', '🤬'];
    const emojiButtonsArray = document.getElementsByClassName("emoji-button");
    var emojiIterator = 0;
    for (let emojiButton of emojiButtonsArray) {
        emojiButton.classList.remove('empty');
        emojiButton.classList.add('emoji-background');
        emojiButton.textContent = defaultEmojis[emojiIterator];
        emojiIterator += 1;
    }

});

window.addEventListener('load', (event) => {
    const now = new Date();
    now.setMinutes(now.getMinutes() - now.getTimezoneOffset());
    document.getElementById('startTime').value = now.toISOString().slice(0, 16);
    var future = now
    future.setMinutes(future.getMinutes() + 30);
    document.getElementById('endTime').value = future.toISOString().slice(0, 16);
});


// POPUP emoji picker
const emojiButtonsArray = document.getElementsByClassName("emoji-button");
const popupLocation = document.getElementById("popupLocation");
const modal = document.getElementById("reg-modal");
const backdrop = document.getElementsByClassName("modal-backdrop");

var emojiCounter = 0;
for (let emojiButton of emojiButtonsArray) {

    emojiButton.addEventListener("click", event => {
        // console.log("Button clicked");
        // console.log("stare emoji -> ", event.target);

        const emojiPicker = document.querySelector('emoji-picker');
        emojiID = 'emoji-' + (emojiCounter += 1);
        console.log(emojiID);

        const setEmoji = e => {
            console.log("zmiana emoji na -> ", event.target);
            event.target.innerHTML = e.detail.unicode;
            emojiPicker.removeEventListener('emoji-click', setEmoji);
            emojiButton.classList.remove('empty');
            emojiButton.classList.add('emoji-background');
            modal.classList.remove('show');
            modal.style.removeProperty('display');
            document.body.removeAttribute("style");
            document.body.removeAttribute("class");
            modal.removeAttribute("aria-modal");
            modal.removeAttribute("role");
            modal.setAttribute('style', 'display: none;');
            modal.setAttribute('aria-hidden', 'true');
            document.body.removeChild(document.body.lastChild);
            document.getElementById(emojiID).setAttribute('value', e.detail.unicode);
            console.log(e.detail.unicode);

            // console.log(e.detail.unicode);
        }

        emojiPicker.addEventListener('emoji-click', setEmoji);
    })
}