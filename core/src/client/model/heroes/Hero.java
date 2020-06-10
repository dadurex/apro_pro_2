package client.model.heroes;

import client.model.Entity;
import client.model.Player;
import client.model.skills.Skill;

import java.util.ArrayList;

/**
 * Class to represent abstract hero
 */
public abstract class Hero extends Entity {
    static int idGen;
    /**
     * Start health
     */
    private final int maxHealth;
    private final int maxMana;
    /**
     * ArrayList of skills
     */
    protected ArrayList<Skill> skills;
    protected int speed;
    /**
     * Owner of that hero
     */
    private Player owner;
    /**
     * Weight of hero (use in order to solve collisions)
     */
    private int weight;
    /**
     * Health of hero
     */
    private int health;
    //    private float healthStatus;
    private int x;
    private int y;
    private int mana;

    //    private double manaStatus;
    public Hero(Player owner, int weight, int startHealth, int health, int speed, int y, int x) {
        this.owner = owner;
        this.speed = speed;
        this.weight = weight;
        this.health = health;
        this.maxHealth = startHealth;
//        this.healthStatus = (float) health / startHealth;
        this.mana = health;
        this.maxMana = startHealth;
//        this.manaStatus = (float) mana/maxHealth;
        this.skills = new ArrayList<>();
        this.x = x;
        this.y = y;
    }

    public static int getIdGen() {
        return idGen;
    }

    public void replenishMana() {
        if (mana + 10 < maxMana) {
            mana += 10;
        } else mana = maxMana;
    }

    @Override
    public String toString() {
        return getClass().getSimpleName();
    }

    @Override
    public int getX() {
        return x;
    }

    @Override
    public void setX(int x) {
        this.x = x;
    }

    @Override
    public int getY() {
        return y;
    }

    @Override
    public void setY(int y) {
        this.y = y;
    }

    public Player getOwner() {
        return owner;
    }

    public void setOwner(Player owner) {
        this.owner = owner;
    }

    public ArrayList<Skill> getSkills() {
        return skills;
    }

    public void setSkills(ArrayList<Skill> skills) {
        this.skills = skills;
    }

    public int getWeight() {
        return weight;
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }

    public int getHealth() {
        return health;
    }

    public void setHealth(int health) {
        this.health = health;
    }

//    public float getHealthStatus() {
//        return healthStatus;
//    }

    public String description() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName() + '\n');
        sb.append("Owned: " + owner.getNick() + '\n');
        sb.append("Speed: " + speed + '\n');
        sb.append("Weight: " + weight + '\n');
        sb.append("Max health: " + maxHealth + '\n');
        sb.append("Current health: " + health + '\n');
//        sb.append("Percent health: " + 100 * healthStatus + "%" + '\n');
        sb.append("Max mana: " + maxMana + '\n');
        sb.append("Current mana: " + mana + '\n');
//        sb.append("Percent mana: " + 100 * mana + "%" + '\n');
        return sb.toString();
    }

    public int getSpeed() {
        return speed;
    }
}