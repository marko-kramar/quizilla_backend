let QuizillaRest = function() {};

QuizillaRest.fetchFromUrl = function(url, successCallback) {
    fetch(url)
        .then((response) => response.json())
        .then((data) => {
            successCallback(data);
        });
};

QuizillaRest.saveEntity = function(uri, entity, method, successCallback) {
    fetch(uri, {
        method: method,
        headers: {
            "Content-Type": "application/json",
        },
        body: JSON.stringify(entity),
    }).then((response) => response.json())
        .then((data) => {
            successCallback(data);
        })
        .catch((error) => {
            console.error("Error:", error);
        });
};

QuizillaRest.deleteEntity = function(endpointUri, id, successCallback) {
    fetch(endpointUri + "/" + id, {
        method: "DELETE"
    }).then(() => {
        successCallback();
    }).catch((error) => {
        console.error("Error:", error);
    });
};