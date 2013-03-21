package net.minekingdom.Sepia;

import java.text.ParseException;
import java.util.Map;

import net.minekingdom.Sepia.environment.Expression;
import net.minekingdom.Sepia.environment.heap.Heap;

import org.spout.api.chat.ChatArguments;
import org.spout.api.chat.Placeholder;
import org.spout.api.chat.style.ChatStyle;
import org.spout.api.event.EventHandler;
import org.spout.api.event.Listener;
import org.spout.api.event.Order;
import org.spout.api.event.server.PreCommandEvent;

public class SepiaListener implements Listener {

    private Heap heap;

    public SepiaListener() {
        this.heap = new Heap();
    }

    @EventHandler(order = Order.EARLY)
    public void onCommand(PreCommandEvent event) {
        ChatArguments args = event.getArguments();
        Map<Placeholder, ChatArguments> placeholders = args.getPlaceholders();

        String str = event.getCommand() + " " + args.toFormatString();
        int start = str.length() + 2;
        while ((start = str.lastIndexOf("<?", start - 2)) != -1) {
            int end = str.indexOf("?>", start + 2);

            if (end != -1 && start + 2 < end) {
                String eval = str.substring(start + 2, end);
                try {
                    Expression expr = Expression.build(eval);
                    expr.populate(this.heap);
                    str = str.substring(0, start) + expr.evaluate(event.getCommandSource()) + str.substring(end + 2);
                } catch (ParseException ex) {
                    event.getCommandSource().sendMessage(ChatStyle.RED, "Error while parsing expression : ", ex.getMessage(), " at position ", ex.getErrorOffset());
                    event.setCancelled(true);
                    return;
                } catch (Exception ex) {
                    event.getCommandSource().sendMessage(ChatStyle.RED, "Error while evalutating expression : ", ex.getMessage());
                    event.setCancelled(true);
                    return;
                }
            }
        }
        
        int space = str.indexOf(' ');
        
        if (space == -1) {
            event.setCommand(str);
            event.setArguments(new ChatArguments());
            return;
        }
        
        args = ChatArguments.fromFormatString(str.substring(space));
        for (Map.Entry<Placeholder, ChatArguments> entry : placeholders.entrySet()) {
            if (args.hasPlaceholder(entry.getKey())) {
                args.setPlaceHolder(entry.getKey(), entry.getValue());
            }
        }

        event.setCommand(str.substring(0, space));
        event.setArguments(args);
    }

}
