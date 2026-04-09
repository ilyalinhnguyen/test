import { useCreateBlockNote } from "@blocknote/react";
import { BlockNoteView } from "@blocknote/mantine";
import "@blocknote/mantine/style.css";

export default function App() {
  const editor = useCreateBlockNote();

  return (
    <main className="mx-auto max-w-5xl p-8 text-slate-200">
      <h1 className="mb-3 text-3xl font-bold">WikiLive Frontend MVP</h1>
      <p className="mb-6 text-slate-300">
        Minimal React + TanStack + Tailwind + BlockNote setup.
      </p>

      <section className="rounded-lg border border-slate-700 bg-slate-900 p-4">
        <BlockNoteView editor={editor} />
      </section>

      <section className="mt-6 rounded-lg border border-slate-700 bg-slate-900 p-4">
        <p className="text-sm text-slate-300">Backend probes:</p>
        <p className="font-mono text-sky-300">/api/java/health</p>
        <p className="font-mono text-sky-300">/api/go/health</p>
      </section>
    </main>
  );
}
