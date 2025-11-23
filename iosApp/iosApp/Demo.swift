import SwiftUI

struct Demo: View {
    var body: some View {
        VStack {
            Text("aff2")
            Image(systemName: "person")
                .font(.title)
                .bold()
            Image(systemName: "gear")
                .font(.title)
                .bold()
            
                .foregroundColor(.red)
            Text("test")
        }
        .padding()
    }
}

#Preview {
    Demo()
}
