package org.powbot.krulvis.slayer.data

import org.powbot.api.rt4.Widget
import org.powbot.api.rt4.Widgets
import org.powbot.krulvis.slayer.data.Dungeon.*
import java.util.regex.Pattern


object Slayer {

    val WIDGET_ID = 231

    fun widget(): Widget = Widgets.widget(WIDGET_ID)


    //NPC messages
    private val NPC_ASSIGN_MESSAGE: Pattern =
        Pattern.compile(".*(?:Your new task is to kill|You are to bring balance to)\\s*(?<amount>\\d+) (?<name>.+?)(?: (?:in|on|south of) (?:the )?(?<location>.+))?\\.")
    private val NPC_ASSIGN_BOSS_MESSAGE: Pattern =
        Pattern.compile("^(?:Excellent\\. )?You're now assigned to (?:kill|bring balance to) (?:the )?(.*) (\\d+) times.*Your reward point tally is (.*)\\.$")
    private val NPC_ASSIGN_FIRST_MESSAGE: Pattern =
        Pattern.compile("^We'll start you off (?:hunting|bringing balance to) (.*), you'll need to kill (\\d*) of them\\.$")
    private val NPC_CURRENT_MESSAGE: Pattern =
        Pattern.compile("^You're (?:still(?: meant to be)?|currently assigned to) (?:hunting|bringing balance to|kill|bring balance to|slaying) (?<name>.+?)(?: (?:in|on|south of) (?:the )?(?<location>.+))?(?:, with|; (?:you have|only)) (?<amount>\\d+)(?: more)? to go\\..*")

    //Reward UI
    private val REWARD_POINTS: Pattern = Pattern.compile("Reward points: ((?:\\d+,)*\\d+)")


    /**
     * Parse the task from the chat component
     */
    fun parseTask(): SlayerTask? {
        val widget = widget()
        if (!widget.valid()) return null
        val master = Master.forName(widget.component(3).text())
        val textComp = widget().components()
            .firstOrNull { it.text().contains("Your new task is") } ?: return null
        val text = textComp.text().sanitizeMultilineText()

        val assignMatcher = NPC_ASSIGN_MESSAGE.matcher(text)
        if (assignMatcher.find()) {
            val name: String = assignMatcher.group("name")
            val amount: Int = assignMatcher.group("amount").toInt()
            val location: String = assignMatcher.group("location")
//            setTask(name, amount, amount, location)
        }
        return null
    }

    /**
     * Replaces all &lt;br&gt; delimited text with spaces and all multiple continuous
     * spaces with single space
     *
     * @param str The string to sanitize
     * @return sanitized string
     */
    fun String.sanitizeMultilineText(): String {
        return replace("-<br>".toRegex(), "-")
            .replace("<br>".toRegex(), " ")
            .replace("[ ]+".toRegex(), " ")
    }

}