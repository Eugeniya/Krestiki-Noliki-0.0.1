Добавлен АИ,реализованнный с помощью метода минимакс.
Исправления, доработки.

1. Разбила по пакетам (AI, exception, field, player, UI).
2. Класс Field 
    - метод getCell - взможно неверно поняла логику архитекторов, но метод возвращал неверное значени. Исправила.
    - добавила методе clone() для создания копии объекта Field

3. Добавлен класс Player (у архитекторов он был интерфейсом, переделала в абстрактный). 
4. Добавлены два класса Computer и Human, наследуются от Player.
5. Добавлен класс AI. Основные члены:
  - константы WIN LOSE DRAW NONE- веса для текущих состояний игрвого поля
   - константа DEPHT - глубина обхода дерева, сейчас cтоит 3, изменяя ее можно варьировать "интеллект". Чем больше значение, тем глубже обход.
   - метод calculatingMove - в зависимотси от выбранного уровня сложности difficulty, вызывает 3 различных метода.   
   - метод getEasyMove для текущего состояния игрового поля получает значение весов возможных вариантов. Далее в полученном массиве выбирает наилучший вариант, возвращает CellInfo.
  - метод minimax - реализация метода минимакс
  - метод heuristic определяет победу для заданного значения клетки (Х или 0)
   - метод children - возвращает потомка для текущего поля.

Не поняла зачем в описании метода значение INFINITY - нигде его не использовала.

проверила на нескольких вариантах игры, получилась ничья (первым ходит человек (крестиком)).

Пример (в методе main)

            game.nextTurn(game.currentPlayer().onMove(2, 0));
            game.nextTurn(computer.doMove(game.getField()));

_ _ X
_ 0 _
  _ 

            game.nextTurn(game.currentPlayer().onMove(2, 1));
            game.nextTurn(computer.doMove(game.getField()));

_ _ X
_ 0 X
_ _ 0

            game.nextTurn(game.currentPlayer().onMove(0, 0));
            game.nextTurn(computer.doMove(game.getField()));

X 0 X
_ 0 X
_ _ 0

            game.nextTurn(game.currentPlayer().onMove(1, 2));
            game.nextTurn(computer.doMove(game.getField()));
 
X 0 X
0 0 X
_ X 0
