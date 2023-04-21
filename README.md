# KFC-Simulator
 Качественно проработанный самодельный игровой движок, в комплекте с которым идёт игра:)

3500 строчек зубодробительного кода.

KFCSimulator - игра на двоих, где вам предстоит забег по торговому центру в поисках бургеров, попутно избегая врагов.

Управление у игроков - WASD и стрелочки.

Условие победы: собрать все бургеры.

Условие поражения: столкнуться с врагом. Звучит костыльно, однако в игре есть системы здоровья и атаки - только вот у игрока одно очко здоровья:)

##Техническая часть

Всё сделано в лучших традициях паттерна MVC, а также других. У игры есть два независимых интерфейса: на swing и на javafx.

Билд игры (которого здесь нет) запускается из консоли. В качестве аргумента при запуске можно указать одну из двух опций: fx или swing. Это запустит соответствующий интерфейс. Если же аргумент не будет указана, то Вам будет предложено ввести тип интерфейса в консоль уже после запуска (Scanner.next(), все дела).

Рекомендуется играть на версии со swing!!!

Проект поделен на две части: движок (пакет Core) и сама игра (остальные пакеты).

Далее будет проведен краткий обзор на каждую составляющую движка:

###Ядро

Здесь находятся классы для самой игры; сцен, которые находятся внутри игры; игровых объектов и компонентов, которые крепятся к игровым объектам.

Также здесь находятся классы, работающие с входными данными с клавиатуры, со временем, с позицией у игровых объектов и другие.

###Физика

Здесь прописана реализация коллайдеров, которые могут представлять собой многоугольники любого вида

Также здесь находится класс Physics, который и работает со столкновениями между объектами и вызывает у них соответствующие события, связанные с физикой.

###Рендеринг

Что здесь реализовано:
    
    1) Спрайт, который имеет при себе специальный идентификатор.
    2) Дисплей, который берет у спрайта его идентификатор и берет по нему нужное изображение.
    3) Камера, которые транслирует изображение из игрового мира на экран. Этот класс позвоялет грамотно масштабировать изображение и правильно отрисовывать большой игрвоой мир, который не способен уместиться целиком на экране.

###UI

В каком-то смысле, подраздел рендеринга. Здесь находится собственная реализация текст, не зависящая от каких-либо фреймворков.
Этот текст крепится к определенной точке на экране, благодаря чему текст правильно масштабируется и меняет позицию вместе с экраном. 

На этом пока всё...