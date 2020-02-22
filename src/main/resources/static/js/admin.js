const QUESTIONS_TAB = "questions";
const CATEGORIES_TAB = "categories";
const LANGUAGES_TAB = "languages";

$(function() {
    loadQuestions();
    handleTabSwitchingBehavior();
});

function handleTabSwitchingBehavior() {
    $("a[data-toggle='tab']").on("shown.bs.tab", function(e) {
        let tab = $(e.target).data("tab");
        if (tab == QUESTIONS_TAB) {
            loadQuestions();
        } else if (tab == CATEGORIES_TAB) {
            loadCategories();
        } else if (tab == LANGUAGES_TAB) {
            loadLanguages();
        }
    })
};

function loadQuestions() {
    var tableBody = $(".tab-content #questions #questions-table tbody");
    tableBody.html("");

    fetch("api/questions")
        .then((response) => response.json())
        .then((data) => {
            for (let i=0; i<data.length; i++) {
                var questionItem = data[i];
                var rowElem = $(document.createElement("tr")).appendTo(tableBody);
                var rowNumCell = $(document.createElement("th")).appendTo(rowElem).attr("scope", "row");

            }
        });
};

function loadCategories() {
    var tableBody = $(".tab-content #categories #categories-table tbody");
    tableBody.html("");

    fetch("api/categories")
        .then((response) => response.json())
        .then((data) => {
            console.log("Success: ", data);
        });
};

function loadLanguages() {
    var tableBody = $(".tab-content #languages #languages-table tbody");
    tableBody.html("");

    fetch("api/languages")
        .then((response) => response.json())
        .then((data) => {
            console.log("Success: ", data);
        });
};