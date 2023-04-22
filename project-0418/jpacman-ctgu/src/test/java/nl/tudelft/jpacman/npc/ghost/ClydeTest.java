package nl.tudelft.jpacman.npc.ghost;

import nl.tudelft.jpacman.board.BoardFactory;
import nl.tudelft.jpacman.board.Direction;
import nl.tudelft.jpacman.board.Unit;
import nl.tudelft.jpacman.level.*;
import nl.tudelft.jpacman.points.PointCalculatorLoader;
import nl.tudelft.jpacman.sprite.PacManSprites;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.assertj.core.api.Assertions.assertThat;

class ClydeTest {

    final static PacManSprites SPRITE_STORE = new PacManSprites();

    static Level level = null;

    @BeforeAll
    public static void prepare() throws IOException {
        GhostFactory ghostFactory = new GhostFactory(SPRITE_STORE);
        BoardFactory boardFactory = new BoardFactory(SPRITE_STORE);
        LevelFactory levelFactory = new LevelFactory(SPRITE_STORE, ghostFactory, new PointCalculatorLoader().load());
        GhostMapParser ghostMapParser = new GhostMapParser(levelFactory, boardFactory, ghostFactory);
        level = ghostMapParser.parseMap("/wcx-board.txt");
    }

    @Test
    public void nextAiMove() throws InterruptedException {
        PlayerFactory playerFactory = new PlayerFactory(SPRITE_STORE);
        Player player = playerFactory.createPacMan();
        level.registerPlayer(player);
        assertThat(Navigation.findNearest(Player.class, level.getBoard().squareAt(0,0)).getSquare()).isEqualTo(level.getBoard().squareAt(3,1));
        assertThat(Navigation.findNearest(Clyde.class, level.getBoard().squareAt(0,0)).getSquare()).isEqualTo(level.getBoard().squareAt(11,1));
        //关卡运行2s后，Clyde距离Player8个单位，Clyder应该会移动到13,1这个位置，远离Player
        level.start();
        Thread.sleep(2 * 1000);
        level.stop();
        assertThat(Navigation.findNearest(Player.class, level.getBoard().squareAt(0,0)).getSquare()).isEqualTo(level.getBoard().squareAt(3,1));
        assertThat(Navigation.findNearest(Clyde.class, level.getBoard().squareAt(0,0)).getSquare()).isEqualTo(level.getBoard().squareAt(12,1));

        //先把Player向西移动到1,1位置，然后关卡运行2s，Clyde应该会向靠近Player方向移动到9,1位置
        level.start();
        moveByStep(2, player, level, Direction.WEST);
        Thread.sleep(2 * 1000);
        level.stop();
        assertThat(Navigation.findNearest(Player.class, level.getBoard().squareAt(0,0)).getSquare()).isEqualTo(level.getBoard().squareAt(1,1));
        assertThat(Navigation.findNearest(Clyde.class, level.getBoard().squareAt(0,0)).getSquare()).isEqualTo(level.getBoard().squareAt(9,1));

        //再把Player向西移动到7,1位置,然后关卡运行2s，Clyde应该会向远离Player方向移动到14,1位置
        level.start();
        moveByStep(6, player, level, Direction.EAST);
        Thread.sleep(2 * 1000);
        level.stop();
        assertThat(Navigation.findNearest(Player.class, level.getBoard().squareAt(0,0)).getSquare()).isEqualTo(level.getBoard().squareAt(7,1));
        assertThat(Navigation.findNearest(Clyde.class, level.getBoard().squareAt(0,0)).getSquare()).isEqualTo(level.getBoard().squareAt(14,1));

        //再把Player向西移动到8,1位置,然后关卡运行2s，Clyde应该会向远离Player方向移动到14,1位置，因为15，1是墙
        level.start();
        moveByStep(1, player, level, Direction.EAST);
        Thread.sleep(2 * 1000);
        level.stop();
        assertThat(Navigation.findNearest(Player.class, level.getBoard().squareAt(0,0)).getSquare()).isEqualTo(level.getBoard().squareAt(8,1));
        assertThat(Navigation.findNearest(Clyde.class, level.getBoard().squareAt(0,0)).getSquare()).isEqualTo(level.getBoard().squareAt(14,1));

    }
    private void moveByStep(int count, Unit unit, Level level, Direction direction) {
        while (count -- > 0) {
            level.move(unit, direction);
        }
    }


}
