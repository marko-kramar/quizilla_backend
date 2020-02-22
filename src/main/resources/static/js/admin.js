$(function() {
    loadQuestions();
    handleTabSwitchingBehavior();
});

function handleTabSwitchingBehavior() {
    $("a[data-toggle='tab']").on("shown.bs.tab", function(e) {
        let tab = $(e.target).data("tab");
        if (tab === "questions") {
            loadQuestions();
        } else if (tab === "categories") {
            loadCategories();
        } else if (tab === "languages") {
            loadLanguages();
        }
    })
};

function loadQuestions() {
    let tableBody = QuizillaUtil.getClearTableBody($(".tab-content #questions #questions-table tbody"));

    fetch("api/questions")
        .then((response) => response.json())
        .then((questions) => {
            questions.forEach((question, i) => {
                let rowElem = $(document.createElement("tr")).appendTo(tableBody);

                QuizillaUtil.createRowNumCell(i+1, rowElem);
                QuizillaUtil.createDataCell(question.id, rowElem);
                QuizillaUtil.createDataCell(question.question, rowElem);
                QuizillaUtil.createDataCell(question.category.name, rowElem);
                QuizillaUtil.createDataCell(question.language.name, rowElem);
                QuizillaUtil.createDataCell(question.correctAnswerId, rowElem);
            });
        });
};

function loadCategories() {
    let tableBody = QuizillaUtil.getClearTableBody($(".tab-content #categories #categories-table tbody"));

    fetch("api/categories")
        .then((response) => response.json())
        .then((categories) => {
            categories.forEach((category, i) => {
                let rowElem = $(document.createElement("tr")).appendTo(tableBody);

                QuizillaUtil.createRowNumCell(i+1, rowElem);
                QuizillaUtil.createDataCell(category.id, rowElem);
                QuizillaUtil.createDataCell(category.code, rowElem);
                QuizillaUtil.createDataCell(category.name, rowElem);
            });
        });
};

function loadLanguages() {
    let tableBody = QuizillaUtil.getClearTableBody($(".tab-content #languages #languages-table tbody"));

    fetch("api/languages")
        .then((response) => response.json())
        .then((languages) => {
            languages.forEach((language, i) => {
                let rowElem = $(document.createElement("tr")).appendTo(tableBody);

                QuizillaUtil.createRowNumCell(i+1, rowElem);
                QuizillaUtil.createDataCell(language.id, rowElem);
                QuizillaUtil.createDataCell(language.code, rowElem);
                QuizillaUtil.createDataCell(language.name, rowElem);
            });
        });
};