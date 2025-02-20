public class SpellLogger implements SpellObserver{
    public void onSpellAdded(Spell spell) {
        System.out.println("Spell added: " + spell.getClass().getSimpleName());
    }
}
