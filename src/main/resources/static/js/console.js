document.addEventListener('DOMContentLoaded', function () {
    // const startButtons = document.querySelectorAll('.start-button');
    const logElement = document.querySelector('#log-area');

    // 监听启动按钮点击事件
    const videosUpdate = document.querySelector('.videosUpdate');
    const commentsUpdate = document.querySelector('.commentsUpdate');
    const wordCloud = document.querySelector('.wordCloud');
    const pieChart = document.querySelector('.pieChart');
    const barChart = document.querySelector('.barChart');
    const clear = document.querySelector('.clear');

    videosUpdate.addEventListener('click', e =>
        f1(e.target, '/MongoDBAndSpark/API/popularInto')
    );

    commentsUpdate.addEventListener('click', e =>
        f1(e.target, '/MongoDBAndSpark/API/updateComment')
    );

    wordCloud.addEventListener('click', e =>
        f2(e.target, '/MongoDBAndSpark/API/wordCloud')
    );

    pieChart.addEventListener('click', e =>
        f2(e.target, '/MongoDBAndSpark/API/pieChart')
    );

    barChart.addEventListener('click', e =>
        f2(e.target, '/MongoDBAndSpark/API/barChart')
    );

    clear.addEventListener('click', e => {
        e.target.classList.add('loading');
        logElement.innerHTML = '';
        setTimeout(() => e.target.classList.remove('loading'), 50);
    });

    const f1 = (button, s) => {
        const service = button.dataset.service;
        if (button.classList.contains('loading')) {
            return;
        }
        // 添加.loading类，使按钮变成圆圈
        button.classList.add('loading');
        logElement.innerHTML += '服务启动: ' + service + '<br>';
        fetch(s)
            .then(r => r.text())
            .then(result => logElement.innerHTML += service + '完成: ' + result + '<br>')
            .then(() => button.classList.remove('loading'))
            .then(() => logElement.scrollTo({
                    top: logElement.scrollHeight,
                    behavior: "smooth"
                }));
    };
    const f2 = (button, s) => {
        const service = button.dataset.service;
        if (button.classList.contains('loading')) {
            return;
        }
        button.classList.add('loading');
        logElement.innerHTML += '服务启动: ' + service + '<br>';
        fetch(s)
            .then(r => r.blob())
            .then(blob => {
                let imageURL = URL.createObjectURL(blob); // 将文件对象转换为可用于 <img> 的 URL
                let img = document.createElement('img');
                img.style.height = '600px';
                img.style.width = '1200px'
                img.src = imageURL; // 设置 <img> 标签的 src 属性
                logElement.innerHTML += service + '完成:<br>'
                logElement.appendChild(img);
                logElement.appendChild(document.createElement('br'));
            })
            .then(() => button.classList.remove('loading'))
            .then(() => logElement.scrollTo({
                top: logElement.scrollHeight,
                behavior: "smooth"
            }));
    };
});
