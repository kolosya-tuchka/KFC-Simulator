package NewDayNewGame.Scripts;

import NewDayNewGame.Core.Component;
import NewDayNewGame.Core.GameObject;
import NewDayNewGame.Core.Physics.Collisions.Collider;
import NewDayNewGame.Core.Time;
import NewDayNewGame.Scripts.PlayerHealth;

import java.io.IOException;

public class EnemyAttack extends Component {
    private double damage;
    private double attackRate;
    private double attackTimer;

    public EnemyAttack(GameObject gameObject, double damage, double attackRate) {
        super(gameObject);
        this.damage = damage;
        this.attackRate = attackRate;
        this.attackTimer = 0;
    }

    @Override
    protected void update() throws IOException {
        if (attackTimer > 0) {
            attackTimer -= Time.getInstance().deltaTime();
        }
    }

    @Override
    protected void onCollisionEnter(Collider other) {
        if (other.gameObject().hasTag("player") && attackTimer <= 0) {
            other.gameObject().getComponent(PlayerHealth.class).takeDamage(damage);
            attackTimer += attackRate;
        }
    }
}
