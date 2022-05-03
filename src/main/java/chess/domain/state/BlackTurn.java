package chess.domain.state;

import chess.domain.ChessBoard;
import chess.domain.Color;
import chess.domain.position.Position;
import chess.exception.InvalidChessStateException;

public class BlackTurn extends Running {

    protected BlackTurn(ChessBoard chessBoard) {
        super(chessBoard);
    }

    @Override
    public State move(Position source, Position target) {
        if (chessBoard.isTurn(source, Color.WHITE)) {
            throw new InvalidChessStateException("white 진영의 차례가 아닙니다.");
        }

        chessBoard.move(source, target);

        if (chessBoard.isFinished()) {
            return new BlackWin(chessBoard);
        }

        return new WhiteTurn(chessBoard);
    }

    @Override
    public StateType getStateType() {
        return StateType.BLACK_TURN;
    }
}
