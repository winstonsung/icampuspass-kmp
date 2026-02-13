// Standard imports
import SwiftUI

struct NavigationScreen: View {
    var body: some View {
        switch UIDevice.current.userInterfaceIdiom {
        case .pad,
            .mac:
            if #available(iOS 16.0, *) {
                @State var columnVisibility = NavigationSplitViewVisibility.all

                NavigationSplitView(columnVisibility: $columnVisibility) {
                    List {
                        Text("Hello, world!")
                        Text("Hello, world!")
                        Text("Hello, world!")
                        Text("Hello, world!")
                    }
                    .navigationTitle("Hello, world!")
                } detail: {
                    SandboxScreen()
                }
                .navigationSplitViewStyle(.balanced)
            } else {
                SwiftUI.NavigationView {
                    List {
                        Text("Hello, world!")
                        Text("Hello, world!")
                        Text("Hello, world!")
                        Text("Hello, world!")
                    }
                    .navigationTitle("Hello, world!")
 
                    SandboxScreen()
                }
                .navigationViewStyle(.columns)
            }
        default:
            if #available(iOS 18.0, *) {
                TabView() {
                    Tab("Hello, world!", systemImage: "calendar.badge.clock") {
                        SandboxScreen()
                    }

                    Tab("Hello, world!", systemImage: "tray.and.arrow.up.fill") {
                        List {
                            Text("Hello, world!")
                            Text("Hello, world!")
                            Text("Hello, world!")
                            Text("Hello, world!")
                        }
                        .navigationTitle("Hello, world!")
                    }
 
                    Tab("Hello, world!", systemImage: "person.crop.circle.fill") {
                        SandboxScreen()
                    }
                }
            } else {
                TabView {
                    SandboxScreen()
                    .tabItem {
                        Label("Hello, world!", systemImage: "list.dash")
                    }

                    List {
                        Text("Hello, world!")
                        Text("Hello, world!")
                        Text("Hello, world!")
                        Text("Hello, world!")
                    }
                    .navigationTitle("Hello, world!")
                    .tabItem {
                        Label("Hello, world!", systemImage: "list.dash")
                    }

                    SandboxScreen()
                        .tabItem {
                            Label("Hello, world!", systemImage: "list.dash")
                        }
                }
            }
        }

    }
}

#Preview {
    NavigationScreen()
}
