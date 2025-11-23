import SwiftUI

struct Demo: View {
    var body: some View {
        VStack {
            Text("aff2")
            Image(systemName: "person")
                .font(.title)
                .bold()
            Text("test")
        }
        .padding()
    }
}

#Preview {
    Demo()
}
