function getY(index) {
        return Math.trunc(index / 3);
    }

function getX(index) {
    return index % 3;
}
function updateCells(cells) {
    cells.forEach((c, index) => {
        if(board[getY(index)][getX(index)] == 1) {
            c.textContent = 'O';
        } else if(board[getY(index)][getX(index)] == -1) {
            c.textContent = 'X';
        }
    });
}
const cells = document.querySelectorAll(".cell");
let board = [
  [0, 0, 0],
  [0, 0, 0],
  [0, 0, 0]
];

const currentPlayer = 1;
let moveCount = 0;
let result = -1;
cells.forEach((c, index) => {
    c.addEventListener("click", () => {
        console.log(`${index}번째 버튼 클릭함!`);
        if(c.textContent.trim() !== '' || result === 1) {
            return
        }
        moveCount++;
        board[getY(index)][getX(index)] = currentPlayer;
        updateCells(cells);

        if(moveCount < 9) {
            fetch("/api/play/tictactoe", {
                method: "POST",
                headers: {"Content-Type": "application/json"
                },
                body: JSON.stringify({
                    board,
                    y: getY(index),
                    x: getX(index),
                    prevPlayer: currentPlayer,
                    moveCount,
                    result: -1
                })
            }).then(response => {
                console.log(response);
                return response.json();
            }).then(data => {
                console.log(data);
                board = data.board;
                moveCount = data.moveCount;
                result = data.result;
                updateCells(cells);
            });
        }
    });
});