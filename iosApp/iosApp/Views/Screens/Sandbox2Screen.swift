// Standard imports
import SwiftUI

@available(iOS 16.0, *)
struct Sandbox2Screen: View {
    var body: some View {
        TabView {
            NavigationStack {
                HomeView()
            }
            .tabItem {
                Label("Home", systemImage: "house")
            }

            NavigationStack {
                SettingsView()
            }
            .tabItem {
                Label("Settings", systemImage: "gear")
            }
        }
    }
}

@available(iOS 16.0, *)
struct HomeView: View {
    var body: some View {
        ScrollView {
            VStack {
                HStack {
                    Text("Today")
                        .font(.largeTitle.bold())

                    Spacer()

                    NavigationLink {
                        Text("Person View")
                    } label: {
                        Image(systemName: "person.crop.circle")
                            .font(.largeTitle)
                            .foregroundColor(.blue)
                    }
                }

                NavigationLink(destination: DetailView()) {
                    Text("Go to Detail View (hides Tab Bar)")
                        .padding()
                        .background(Color.blue)
                        .foregroundColor(.white)
                        .cornerRadius(10)
                }
            }
            .padding()
        }
        .navigationTitle("Home")
        .navigationBarHidden(true)
    }
}

@available(iOS 16.0, *)
struct DetailView: View {
    var body: some View {
        Text("Detail View")
            .navigationTitle("Detail")
            // .toolbar(.hidden, for: .tabBar)
    }
}

@available(iOS 16.0, *)
struct SettingsView: View {
    var body: some View {
        Text("Settings View")
            .navigationTitle("Settings")
    }
}

@available(iOS 16.0, *)
#Preview {
    Sandbox2Screen()
}
