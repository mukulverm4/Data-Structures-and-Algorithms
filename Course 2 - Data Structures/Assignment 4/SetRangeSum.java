import java.io.*;
import java.util.*;

/*
Input Format. Initially the set 𝑆 is empty. The first line contains 𝑛 — the number of operations. The next
𝑛 lines contain operations. Each operation is one of the following:
∙ “+ i" — which means add some integer (not 𝑖, see below) to 𝑆,
∙ “- i" — which means del some integer (not 𝑖, see below)from 𝑆,
∙ “? i" — which means find some integer (not 𝑖, see below)in 𝑆,
∙ “s l r" — which means compute the sum of all elements of 𝑆 within some range of values (not
from 𝑙 to 𝑟, see below).
However, to make sure that your solution can work in an online fashion, each request will actually
depend on the result of the last sum request. Denote 𝑀 = 1 000 000 001. At any moment, let 𝑥 be
the result of the last sum operation, or just 0 if there were no sum operations before. Then
∙ “+ i" means add((𝑖 + 𝑥) mod 𝑀),
∙ “- i" means del((𝑖 + 𝑥) mod 𝑀),
∙ “? i" means find((𝑖 + 𝑥) mod 𝑀),
∙ “s l r" means sum((𝑙 + 𝑥) mod 𝑀, (𝑟 + 𝑥) mod 𝑀).

Output Format. For each find request, just output “Found" or “Not found" (without quotes; note that the
first letter is capital) depending on whether (𝑖+𝑥) mod 𝑀 is in 𝑆 or not. For each sum query, output
the sum of all the values 𝑣 in 𝑆 such that ((𝑙+𝑥) mod 𝑀) ≤ 𝑣 ≤ ((𝑟+𝑥) mod 𝑀) (it is guaranteed that
in all the tests ((𝑙 + 𝑥) mod 𝑀) ≤ ((𝑟 + 𝑥) mod 𝑀)), where 𝑥 is the result of the last sum operation
or 0 if there was no previous sum operation.
*/

public class SetRangeSum {

    BufferedReader br;
    PrintWriter out;
    StringTokenizer st;
    boolean eof;

    // Splay tree implementation

    // Vertex of a splay tree
    class Vertex {
        int key;
        // Sum of all the keys in the subtree - remember to update
        // it after each operation that changes the tree.
        long sum;
        Vertex left;
        Vertex right;
        Vertex parent;

        Vertex(int key, long sum, Vertex left, Vertex right, Vertex parent) {
            this.key = key;
            this.sum = sum;
            this.left = left;
            this.right = right;
            this.parent = parent;
        }
    }

    void update(Vertex v) {
        if (v == null) return;
        v.sum = v.key + (v.left != null ? v.left.sum : 0) + (v.right != null ? v.right.sum : 0);
        if (v.left != null) {
            v.left.parent = v;
        }
        if (v.right != null) {
            v.right.parent = v;
        }
    }

    void smallRotation(Vertex v) {
        Vertex parent = v.parent;
        if (parent == null) {
            return;
        }
        Vertex grandparent = v.parent.parent;
        if (parent.left == v) {
            Vertex m = v.right;
            v.right = parent;
            parent.left = m;
        } else {
            Vertex m = v.left;
            v.left = parent;
            parent.right = m;
        }
        update(parent);
        update(v);
        v.parent = grandparent;
        if (grandparent != null) {
            if (grandparent.left == parent) {
                grandparent.left = v;
            } else {
                grandparent.right = v;
            }
        }
    }

    void bigRotation(Vertex v) {
        if (v.parent.left == v && v.parent.parent.left == v.parent) {
            // Zig-zig
            smallRotation(v.parent);
            smallRotation(v);
        } else if (v.parent.right == v && v.parent.parent.right == v.parent) {
            // Zig-zig
            smallRotation(v.parent);
            smallRotation(v);
        } else {
            // Zig-zag
            smallRotation(v);
            smallRotation(v);
        }
    }

    // Makes splay of the given vertex and returns the new root.
    Vertex splay(Vertex v) {
        if (v == null) return null;
        while (v.parent != null) {
            if (v.parent.parent == null) {
                smallRotation(v);
                break;
            }
            bigRotation(v);
        }
        return v;
    }

    class VertexPair {
        Vertex left;
        Vertex right;
        VertexPair() {
        }
        VertexPair(Vertex left, Vertex right) {
            this.left = left;
            this.right = right;
        }
    }

    // Searches for the given key in the tree with the given root
    // and calls splay for the deepest visited node after that.
    // Returns pair of the result and the new root.
    // If found, result is a pointer to the node with the given key.
    // Otherwise, result is a pointer to the node with the smallest
    // bigger key (next value in the order).
    // If the key is bigger than all keys in the tree,
    // then result is null.
    VertexPair find(Vertex root, int key) {
        Vertex v = root;
        Vertex last = root;
        Vertex next = null;
        while (v != null) {
            if (v.key >= key && (next == null || v.key < next.key)) {
                next = v;
            }
            last = v;
            if (v.key == key) {
                break;
            }
            if (v.key < key) {
                v = v.right;
            } else {
                v = v.left;
            }
        }
        root = splay(last);
        return new VertexPair(next, root);
    }

    VertexPair split(Vertex root, int key) {
        VertexPair result = new VertexPair();
        VertexPair findAndRoot = find(root, key);
        root = findAndRoot.right;
        result.right = findAndRoot.left;
        if (result.right == null) {
            result.left = root;
            return result;
        }
        result.right = splay(result.right);
        result.left = result.right.left;
        result.right.left = null;
        if (result.left != null) {
            result.left.parent = null;
        }
        update(result.left);
        update(result.right);
        return result;
    }

    Vertex merge(Vertex left, Vertex right) {
        if (left == null) return right;
        if (right == null) return left;
        while (right.left != null) {
            right = right.left;
        }
        right = splay(right);
        right.left = left;
        update(right);
        return right;
    }

    // Code that uses splay tree to solve the problem

    Vertex root = null;

    void insert(int x) {
        Vertex left = null;
        Vertex right = null;
        Vertex new_vertex = null;
        VertexPair leftRight = split(root, x);
        left = leftRight.left;
        right = leftRight.right;
        if (right == null || right.key != x) {
            new_vertex = new Vertex(x, x, null, null, null);
        }
        root = merge(merge(left, new_vertex), right);
    }

    void erase(int x) {

       	Vertex next = next(x, root);
       	if(next!=null)
    	root = splay(next);
    	VertexPair found = find(root, x);
    	Vertex node = found.left;
    	root = found.right;
    	if(node!=null&&node.key==x) delete(node);

    }
    
    void delete(Vertex v) {

    	if(v.right!=null) {
    	v.right.left= v.left;
    	v.right.parent = null;
    	if(v.left!=null)
    	v.left.parent=v.right;
    	
    	root = v.right;}
    	else if(v.left!=null) {
    		v.left.parent=null;
    		root = v.left;

    	}else {
    		root = null;
    	}
    	update(root);
    }

    boolean find(int x) {

    	VertexPair pair = find(root, x);
    	root = pair.right;
    	if(pair.left!=null&&pair.left.key==x) return true;
    	else return false;
    }

    long sum(int from, int to) {

        VertexPair leftMiddle = split(root, from);
        Vertex left = leftMiddle.left;

        Vertex middle = leftMiddle.right;
     
        long ans = 0;
        VertexPair middleRight = split(middle, to + 1);
        middle = middleRight.left;

        Vertex right = middleRight.right;
  
        if(middle!=null)
         ans = middle.sum;

       
        root = merge(merge(left, middle),right);

        return ans;
    }
    void inOrder(Vertex root) {if(root!=null) {
    	if(root.left!=null) inOrder(root.left);
    	System.out.print("> "+root.key);
    	if(root.right!=null) inOrder(root.right);}
    }
    
    Vertex next(int key, Vertex root) {
    	Vertex next = null;
    	if(root==null) return null;

    	if(root.key==key) {
    		if(root.right!=null) return leftDes(root.right);
    		else return rightAnc(root);
    	}

    		
    	else if(root.key>key) {
    		next =next(key,root.left);
    	}
    	else {
    		next =next(key,root.right);
    	}
    	
    	
		return next;
    	
    }
    
    Vertex leftDes(Vertex v) {
    	
    	if(v.left!=null) {
    		return leftDes(v.left);
    	}
    	else return v;
    	
    	
    }
    
    Vertex rightAnc(Vertex v) {
    	if(v.parent!=null) 
    		if(v.parent.left==v)
    			return v.parent;
    		else return rightAnc(v.parent);
    	else return null;
    }

    public static final int MODULO = 1000000001;

    void solve() throws IOException {
        int n = nextInt();
        int last_sum_result = 0;
        for (int i = 0; i < n; i++) {
            char type = nextChar();
            switch (type) {
                case '+' : {
                    int x = nextInt();
                    insert((x + last_sum_result) % MODULO);
                } break;
                case '-' : {
                	
                    int x = nextInt();

                    erase((x + last_sum_result) % MODULO);
                } break;
                case '?' : {
                    int x = nextInt();
                    out.println(find((x + last_sum_result) % MODULO) ? "Found" : "Not found");
                } break;
                case 's' : {
                    int l = nextInt();
                    int r = nextInt();
                    long res = sum((l + last_sum_result) % MODULO, (r + last_sum_result) % MODULO);
                  //  out.println("***"+l);
                    out.println(res);
                    last_sum_result = (int)(res % MODULO);
                }
            }

        }
    }

    SetRangeSum() throws IOException {
        br = new BufferedReader(new InputStreamReader(System.in));
        out = new PrintWriter(System.out);
        solve();
        out.close();
    }

    public static void main(String[] args) throws IOException {
        new SetRangeSum();
    }

    String nextToken() {
        while (st == null || !st.hasMoreTokens()) {
            try {
                st = new StringTokenizer(br.readLine());
            } catch (Exception e) {
                eof = true;
                return null;
            }
        }
        return st.nextToken();
    }

    int nextInt() throws IOException {
        return Integer.parseInt(nextToken());
    }
    char nextChar() throws IOException {
        return nextToken().charAt(0);
    }
}
