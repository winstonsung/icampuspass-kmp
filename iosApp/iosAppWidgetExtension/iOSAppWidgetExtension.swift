import SwiftUI
import WidgetKit

struct Provider: TimelineProvider {
    func placeholder(in context: Context) -> SimpleEntry {
        SimpleEntry(date: Date(), emoji: "😀")
    }

    func getSnapshot(in context: Context, completion: @escaping (SimpleEntry) -> ()) {
        let entry = SimpleEntry(date: Date(), emoji: "😀")

        completion(entry)
    }

    func getTimeline(in context: Context, completion: @escaping (Timeline<Entry>) -> ()) {
        var entries: [SimpleEntry] = []

        // Generate a timeline consisting of five entries an hour apart, starting from the current date.
        let currentDate = Date()

        for hourOffset in 0 ..< 5 {
            let entryDate = Calendar.current.date(byAdding: .hour, value: hourOffset, to: currentDate)!

            let entry = SimpleEntry(date: entryDate, emoji: "😀")

            entries.append(entry)
        }

        let timeline = Timeline(entries: entries, policy: .atEnd)

        completion(timeline)
    }

//    func relevances() async -> WidgetRelevances<Void> {
//        // Generate a list containing the contexts this widget is relevant in.
//    }
}

struct SimpleEntry: TimelineEntry {
    let date: Date
    let emoji: String
}

struct iOSAppWidgetExtensionEntryView : View {
    var entry: Provider.Entry

    var body: some View {
        VStack {
            Text(
                String(
                    localized: "widget_time_label",
                    defaultValue: "Time:",
                    table: "iOSAppWidgetExtension",
                    comment: "The label of time for widget."
                )
            )

            Text(entry.date, style: .time)

            Text(
                String(
                    localized: "widget_emoji_label",
                    defaultValue: "Emoji:",
                    table: "iOSAppWidgetExtension",
                    comment: "The label of emoji for widget."
                )
            )

            Text(entry.emoji)
        }
    }
}

struct iOSAppWidgetExtension: Widget {
    let kind: String = "iOSAppWidgetExtension"

    var body: some WidgetConfiguration {
        StaticConfiguration(kind: kind, provider: Provider()) { entry in
            if #available(iOS 17.0, *) {
                iOSAppWidgetExtensionEntryView(entry: entry)
                .containerBackground(.fill.tertiary, for: .widget)
            } else {
                iOSAppWidgetExtensionEntryView(entry: entry)
                .padding()
                .background()
            }
        }
        .configurationDisplayName(
            String(
                localized: "widget_display_name",
                defaultValue: "Widget Display Name",
                table: "iOSAppWidgetExtension",
                comment: "The display name of widget."
            )
        )
        .description(
            String(
                localized: "widget_description",
                defaultValue: "Widget description.",
                table: "iOSAppWidgetExtension",
                comment: "The description of widget."
            )
        )
    }
}

@available(iOS 17.0, *)
#Preview(as: .systemSmall) {
    iOSAppWidgetExtension()
} timeline: {
    SimpleEntry(date: .now, emoji: "😀")

    SimpleEntry(date: .now, emoji: "🤩")
}
