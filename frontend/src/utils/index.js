export function getUniqueItem(data, uniqueKey) {

    let arr = [{ id: 1, name: "Root" }];

    const iterate = (obj) => {
        arr.push({ id: obj.id, name: obj.name });
        if (obj.children != undefined && obj.children.length !== 0) {
            obj.children.forEach((val) => iterate(val));
        }
    };

    data.forEach((obj) => {
        iterate(obj);
    });

    const key = uniqueKey;

    return [...new Map(arr.map((item) => [item[key], item])).values()];
}

export function splitArrayForDeleteAndAddArrays(originalPhotos, photosForChange) {
    // исходный массив [{10},{11},{12}]
    // массив для запросов [{12},{13},{14},{15}]
    // значит, что если в массиве для запросов есть элементы
    // из исходного массива, то эти элементы подлежат удалению,
    // а если в исходном их нет, то эти элементы подлежат добавлению
    // удалить [{12}] добавить [{13},{14},{15}] новый [{10},{11},{13},{14},{15}]

    let photosForDelete = []; // массив для запроса на удаление
    let photosForAdd = []; // массив для запроса на добавление

    let bucketPhotos = photosForChange.concat(originalPhotos);
    let allUniquePhotos = bucketPhotos.filter((item, pos) => bucketPhotos.indexOf(item) === pos);

    //console.log(allUniquePhotos);

    allUniquePhotos.forEach((photo) => {
        let index = originalPhotos.indexOf(photo);
        let jndex = photosForChange.indexOf(photo);
        if (index == -1 && jndex != -1) {
            photosForAdd.push(photo);
        }
        if (index != -1 && jndex == -1) {
            photosForDelete.push(photo);
        }
    });

    return [photosForAdd, photosForDelete]
}

export function getJWT(){
    return JSON.parse(localStorage.getItem('user')).jwtToken;
}

export function isAdmin(){

    let user = JSON.parse(localStorage.getItem('user'))
    let status = user ? user.role == "ADMIN" ? true : false : false;

    return status;
}

export function getAdmin(){
    return JSON.parse(localStorage.getItem('user'))
}