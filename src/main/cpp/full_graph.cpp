#include <iostream>
#include <unordered_map>
#include <unordered_set>

using namespace std;

string if_graph_full();

const string YES = "YES";
const string NO = "NO";

int main() {
    cout << if_graph_full() << endl;
    return 0;
}

string if_graph_full() {
    int vertexes, edges;
    cin >> vertexes >> edges;
    unordered_map<int, unordered_set<int> > graph;
    if (vertexes == 1) {
        return YES;
    }
    for (int i = 0; i < edges; i++) {
        int from, to;
        cin >> from >> to;
        if (from != to) {
            unordered_set<int> adjacent;
            if (graph.find(from) != graph.end()) {
                graph.at(from).insert(to);
            } else {
                adjacent.insert(to);
                graph.insert(pair<int, unordered_set<int> >(from, adjacent));
            }

            unordered_set<int> inversed_adjacent;
            if (graph.find(to) != graph.end()) {
                graph.at(to).insert(from);
            } else {
                inversed_adjacent.insert(from);
                graph.insert(pair<int, unordered_set<int> >(to, inversed_adjacent));
            }
        }
    }
    for (int i = 1; i <= vertexes; i++) {
        if (graph.find(i) == graph.end()
            || graph.at(i).size() != vertexes - 1) {
            return NO;
        }
    }
    return YES;
}

