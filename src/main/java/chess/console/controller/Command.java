package chess.console.controller;

import chess.console.view.OutputView;
import chess.domain.ChessGame;
import chess.domain.Color;
import chess.domain.position.Position;
import java.util.Arrays;
import java.util.List;
import java.util.function.BiConsumer;

public enum Command {

    START("start", (chessGame, arguments) -> {
        chessGame.start();
        OutputView.printChessBoard(chessGame.board().getBoard());
    }),

    END("end", (chessGame, arguments) -> {
        chessGame.end();
    }),

    MOVE("move", (chessGame, arguments) -> {
        chessGame.move(arguments.get(0), arguments.get(1));
        OutputView.printChessBoard(chessGame.board().getBoard());
    }),

    STATUS("status", (chessGame, arguments) -> {
        double whiteScore = chessGame.score(Color.WHITE);
        double blackScore = chessGame.score(Color.BLACK);
        OutputView.printStatusMessage(whiteScore, blackScore);
    });

    private final String type;
    private final BiConsumer<ChessGame, List<Position>> consumer;

    Command(String type, BiConsumer<ChessGame, List<Position>> consumer) {
        this.type = type;
        this.consumer = consumer;
    }

    public static Command from(String input) {
        return Arrays.stream(values())
                .filter(command -> input.equals(command.type))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 명령입니다."));
    }

    public void execute(ChessGame chessGame, Position source, Position target) {
        consumer.accept(chessGame, List.of(source, target));
    }
}
