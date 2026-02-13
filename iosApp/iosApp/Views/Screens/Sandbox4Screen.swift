// Standard imports
import SwiftUI

@available(iOS 18.0, *)
struct Sandbox4Screen: View {
    @State private var columnVisibility = NavigationSplitViewVisibility.all

    @State private var selection: String? = "Home"

    var body: some View {
        @State var sidebarTitle: String =
        switch UIDevice.current.userInterfaceIdiom {
        case .pad,
                .mac:
            String(
                localized: "sandbox4screen_sidebar_app_name",
                defaultValue: "iCampusPass",
                table: "Sandbox4Screen"
            )
        default:
            switch selection {
            case nil:
                String(
                    localized: "sandbox4screen_sidebar_app_name",
                    defaultValue: "iCampusPass",
                    table: "Sandbox4Screen"
                )
            default:
                String(
                    localized: "sandbox4screen_sidebar_menu",
                    defaultValue: "Menu",
                    table: "Sandbox4Screen"
                )
            }
        }

        NavigationSplitView(
            columnVisibility: $columnVisibility,
            sidebar: {
                List(
                    ["Home", "Class schedule", "Settings", "About"],
                    id: \.self,
                    selection: $selection
                ) { destination in
                    NavigationLink(destination.description, value: destination)
                }
                .navigationTitle(
                    sidebarTitle
                )
            },
            detail: {
                TabView(
                    content: {
                        Tab(
                            String(
                                localized: "detail_tab_1",
                                defaultValue: "Tab 1",
                                table: "Sandbox4Screen"
                            ),
                            systemImage: "swift",
                            content: {
                                Text(
                                    String(
                                        localized: "sandbox4screen_hello_world",
                                        defaultValue: "Hello, world!",
                                        table: "Sandbox4Screen"
                                    )
                                )
                            }
                        )

                        Tab(
                            String(
                                localized: "detail_tab_2",
                                defaultValue: "Tab 2",
                                table: "Sandbox4Screen"
                            ),
                            systemImage: "swift",
                            content: {
                                Text(
                                    String(
                                        localized: "sandbox4screen_hello_world",
                                        defaultValue: "Hello, world!",
                                        table: "Sandbox4Screen"
                                    )
                                )
                                NavigationLink("Home".description, value: "Home")
                            }
                        )
                    }
                )
                .navigationTitle(
                    String(
                        localized: "sandbox4screen_detail",
                        defaultValue: "Detail",
                        table: "Sandbox4Screen"
                    )
                )
            }
        )
        .navigationSplitViewStyle(.balanced)
    }
}

@available(iOS 18.0, *)
#Preview {
    Sandbox4Screen()
}
