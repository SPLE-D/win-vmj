import assert from "node:assert/strict";

const BASE_URL = process.env.BASE_URL ?? "http://localhost:7776";

const routes = {
  eventcreation: {
    path: "eventcreation",
    idKey: "eventId",
    createPayload: (suffix) => ({
      startDate: "2026-07-01",
      endDate: "2026-07-02",
      capacity: "100",
      name: `CRUD Event ${suffix}`,
      location: "Lab Test",
    }),
    updatePayload: (id, suffix) => ({
      eventId: String(id),
      startDate: "2026-07-03",
      endDate: "2026-07-04",
      capacity: "120",
      name: `CRUD Event Updated ${suffix}`,
      location: "Lab Test Updated",
    }),
    markerKey: "name",
    updatedValue: (suffix) => `CRUD Event Updated ${suffix}`,
    detailQueryKey: "eventId",
  },
  typeeventcreation: {
    path: "typeeventcreation",
    idKey: "eventId",
    createPayload: (suffix) => ({
      startDate: "2026-08-01",
      endDate: "2026-08-02",
      capacity: "80",
      name: `CRUD Type Event ${suffix}`,
      location: "Delta Hall",
      eventType: "PUBLIC",
    }),
    updatePayload: (id, suffix) => ({
      eventId: String(id),
      startDate: "2026-08-03",
      endDate: "2026-08-04",
      capacity: "90",
      name: `CRUD Type Event Updated ${suffix}`,
      location: "Delta Hall Updated",
      eventType: "PRIVATE",
    }),
    markerKey: "name",
    updatedValue: (suffix) => `CRUD Type Event Updated ${suffix}`,
    detailQueryKey: "eventId",
    extraAssert: (record) => assert.equal(record.eventType, "PRIVATE"),
  },
  report: {
    path: "report",
    idKey: "reportId",
    createPayload: (suffix, ctx) => ({
      eventId: String(ctx.eventId),
      totalAttendee: "10",
      totalRevenue: "500000",
      summary: `CRUD Report ${suffix}`,
    }),
    updatePayload: (id, suffix, ctx) => ({
      reportId: String(id),
      eventId: String(ctx.eventId),
      totalAttendee: "12",
      totalRevenue: "750000",
      summary: `CRUD Report Updated ${suffix}`,
    }),
    markerKey: "summary",
    updatedValue: (suffix) => `CRUD Report Updated ${suffix}`,
    detailQueryKey: "reportId",
  },
  priorityreport: {
    path: "priorityreport",
    idKey: "reportId",
    createPayload: (suffix, ctx) => ({
      eventId: String(ctx.eventId),
      totalAttendee: "15",
      totalRevenue: "900000",
      summary: `CRUD Priority Report ${suffix}`,
      priorityReport: "HIGH",
    }),
    updatePayload: (id, suffix, ctx) => ({
      reportId: String(id),
      eventId: String(ctx.eventId),
      totalAttendee: "18",
      totalRevenue: "1100000",
      summary: `CRUD Priority Report Updated ${suffix}`,
      priorityReport: "CRITICAL",
    }),
    markerKey: "summary",
    updatedValue: (suffix) => `CRUD Priority Report Updated ${suffix}`,
    detailQueryKey: "reportId",
    extraAssert: (record) => assert.equal(record.priorityReport, "CRITICAL"),
  },
  review: {
    path: "review",
    idKey: "reviewId",
    createPayload: (suffix, ctx) => ({
      eventId: String(ctx.eventId),
      attendeeId: String(ctx.attendeeId),
      rating: "4",
      comment: `CRUD Review ${suffix}`,
    }),
    updatePayload: (id, suffix, ctx) => ({
      reviewId: String(id),
      eventId: String(ctx.eventId),
      attendeeId: String(ctx.attendeeId),
      rating: "5",
      comment: `CRUD Review Updated ${suffix}`,
    }),
    markerKey: "comment",
    updatedValue: (suffix) => `CRUD Review Updated ${suffix}`,
    detailQueryKey: "reviewId",
  },
  reviewanonymous: {
    path: "reviewanonymous",
    idKey: "reviewId",
    createPayload: (suffix, ctx) => ({
      eventId: String(ctx.eventId),
      attendeeId: String(ctx.attendeeId),
      rating: "3",
      comment: `CRUD Anonymous Review ${suffix}`,
      anonymous: true,
    }),
    updatePayload: (id, suffix, ctx) => ({
      reviewId: String(id),
      eventId: String(ctx.eventId),
      attendeeId: String(ctx.attendeeId),
      rating: "4",
      comment: `CRUD Anonymous Review Updated ${suffix}`,
      anonymous: false,
    }),
    markerKey: "comment",
    updatedValue: (suffix) => `CRUD Anonymous Review Updated ${suffix}`,
    detailQueryKey: "reviewId",
    extraAssert: (record) => assert.equal(record.anonymous, false),
  },
  checkin: {
    path: "checkin",
    idKey: "checkInId",
    createPayload: () => ({ attended: false }),
    updatePayload: (id) => ({ checkInId: String(id), attended: true }),
    markerKey: "attended",
    updatedValue: () => true,
    detailQueryKey: "checkInId",
  },
  timestampcheckin: {
    path: "timestampcheckin",
    idKey: "checkInId",
    createPayload: () => ({
      attended: false,
      timestamp: "2026-09-01T10:00:00",
    }),
    updatePayload: (id) => ({
      checkInId: String(id),
      attended: true,
      timestamp: "2026-09-01T11:00:00",
    }),
    markerKey: "attended",
    updatedValue: () => true,
    detailQueryKey: "checkInId",
    extraAssert: (record) => assert.ok(record.timestamp, "timestamp should be present"),
  },
  attendeemanagement: {
    path: "attendeemanagement",
    idKey: "attendeeId",
    createPayload: (suffix) => ({
      phoneNumber: `08123${suffix}`,
      email: `attendee-${suffix}@example.test`,
    }),
    updatePayload: (id, suffix) => ({
      attendeeId: String(id),
      phoneNumber: `08987${suffix}`,
      email: `attendee-updated-${suffix}@example.test`,
    }),
    markerKey: "email",
    updatedValue: (suffix) => `attendee-updated-${suffix}@example.test`,
    detailQueryKey: "attendeeId",
  },
  classattendeemanagement: {
    path: "classattendeemanagement",
    idKey: "attendeeId",
    createPayload: (suffix) => ({
      phoneNumber: `08223${suffix}`,
      email: `class-attendee-${suffix}@example.test`,
      attendeeClass: "REGULAR",
    }),
    updatePayload: (id, suffix) => ({
      attendeeId: String(id),
      phoneNumber: `08887${suffix}`,
      email: `class-attendee-updated-${suffix}@example.test`,
      attendeeClass: "VIP",
    }),
    markerKey: "email",
    updatedValue: (suffix) => `class-attendee-updated-${suffix}@example.test`,
    detailQueryKey: "attendeeId",
    extraAssert: (record) => assert.equal(record.attendeeClass, "VIP"),
  },
  notification: {
    path: "notification",
    idKey: "notifiationId",
    createPayload: (suffix) => ({ content: `CRUD Notification ${suffix}` }),
    updatePayload: (id, suffix) => ({
      notifiationId: String(id),
      content: `CRUD Notification Updated ${suffix}`,
    }),
    markerKey: "content",
    updatedValue: (suffix) => `CRUD Notification Updated ${suffix}`,
    detailQueryKey: "notifiationId",
  },
  targetednotification: {
    path: "targetednotification",
    idKey: "notifiationId",
    createPayload: (suffix) => ({
      content: `CRUD Targeted Notification ${suffix}`,
      target: "101",
    }),
    updatePayload: (id, suffix) => ({
      notifiationId: String(id),
      content: `CRUD Targeted Notification Updated ${suffix}`,
      target: "202",
    }),
    markerKey: "content",
    updatedValue: (suffix) => `CRUD Targeted Notification Updated ${suffix}`,
    detailQueryKey: "notifiationId",
    extraAssert: (record) => assert.equal(Number(record.target), 202),
  },
};

const cleanup = [];

const moduleGroups = {
  core: [
    "eventcreation",
    "report",
    "review",
    "checkin",
    "attendeemanagement",
    "notification",
  ],
  delta: [
    "typeeventcreation",
    "priorityreport",
    "reviewanonymous",
    "timestampcheckin",
    "classattendeemanagement",
    "targetednotification",
  ],
};

moduleGroups.all = Object.keys(routes);

function parseModules() {
  const modulesArg =
    process.argv.find((arg) => arg.startsWith("--modules="))?.split("=")[1]
    ?? process.env.TEST_MODULES;

  if (!modulesArg) {
    console.log("No modules selected.");
    console.log("Usage:");
    console.log("  node tests/alldelta-crud.test.mjs --modules=typeeventcreation,reviewanonymous");
    console.log("  node tests/alldelta-crud.test.mjs --modules=delta");
    console.log("  node tests/alldelta-crud.test.mjs --modules=all");
    console.log("");
    console.log("Available modules:");
    console.log(`  ${Object.keys(routes).join(", ")}`);
    return [];
  }

  const selected = modulesArg
    .split(",")
    .map((item) => item.trim().toLowerCase())
    .filter(Boolean)
    .flatMap((item) => moduleGroups[item] ?? [item]);

  const unique = [...new Set(selected)];

  const invalid = unique.filter((name) => !routes[name]);
  assert.equal(
    invalid.length,
    0,
    `Unknown module(s): ${invalid.join(", ")}. Available: ${Object.keys(routes).join(", ")}`,
  );

  return unique;
}

function endpoint(path, action, query = undefined) {
  const url = new URL(`${BASE_URL.replace(/\/$/, "")}/call/${path}/${action}`);
  if (query) {
    for (const [key, value] of Object.entries(query)) {
      url.searchParams.set(key, String(value));
    }
  }
  return url;
}

async function request(method, path, action, body = undefined, query = undefined) {
  const response = await fetch(endpoint(path, action, query), {
    method,
    headers: body === undefined ? undefined : { "Content-Type": "application/json" },
    body: body === undefined ? undefined : JSON.stringify(body),
  });

  const raw = await response.text();
  let parsed;
  try {
    parsed = raw ? JSON.parse(raw) : null;
  } catch {
    parsed = raw;
  }

  assert.ok(
    response.ok,
    `${method} ${response.url} failed with ${response.status}: ${raw}`,
  );

  if (parsed && typeof parsed === "object" && Object.hasOwn(parsed, "data")) {
    return parsed.data;
  }

  return parsed;
}

function asArray(value) {
  assert.ok(Array.isArray(value), `Expected array response, got ${JSON.stringify(value)}`);
  return value;
}

function idOf(record, spec) {
  const id = record?.[spec.idKey];
  assert.notEqual(id, undefined, `Missing ${spec.idKey} in ${JSON.stringify(record)}`);
  return Number(id);
}

function findById(records, spec, id) {
  return asArray(records).find((record) => Number(record?.[spec.idKey]) === Number(id));
}

function findByMarker(records, spec, expectedValue) {
  return asArray(records).find((record) => record?.[spec.markerKey] === expectedValue);
}

async function createRecord(name, suffix, ctx = {}) {
  const spec = routes[name];
  const payload = spec.createPayload(suffix, ctx);
  const list = await request("POST", spec.path, "save", payload);
  const created = findByMarker(list, spec, payload[spec.markerKey]) ?? asArray(list).at(-1);
  const id = idOf(created, spec);
  cleanup.push({ name, id });
  return { id, record: created };
}

async function deleteRecord(name, id) {
  const spec = routes[name];
  await request("POST", spec.path, "delete", { [spec.idKey]: String(id) });
}

async function crud(name, ctx = {}) {
  const suffix = `${Date.now()}-${Math.floor(Math.random() * 100000)}`;
  const spec = routes[name];

  const { id } = await createRecord(name, suffix, ctx);

  const detail = await request(
    "GET",
    spec.path,
    "detail",
    undefined,
    { [spec.detailQueryKey]: id },
  );
  assert.equal(Number(detail?.[spec.idKey]), id, `${name} detail should return created id`);

  const updatePayload = spec.updatePayload(id, suffix, ctx);
  const updated = await request("POST", spec.path, "update", updatePayload);
  assert.equal(updated?.[spec.markerKey], spec.updatedValue(suffix));

  const listAfterUpdate = await request("POST", spec.path, "list", {});
  const listed = findById(listAfterUpdate, spec, id);
  assert.ok(listed, `${name} should be present in list after update`);
  assert.equal(listed[spec.markerKey], spec.updatedValue(suffix));
  spec.extraAssert?.(listed);

  await deleteRecord(name, id);
  cleanup.splice(cleanup.findIndex((item) => item.name === name && item.id === id), 1);

  const listAfterDelete = await request("POST", spec.path, "list", {});
  assert.equal(findById(listAfterDelete, spec, id), undefined, `${name} should be deleted`);

  console.log(`ok - ${name} CRUD`);
}

async function withContext() {
  const suffix = `${Date.now()}-${Math.floor(Math.random() * 100000)}`;

  const event = await createRecord("eventcreation", `ctx-event-${suffix}`);
  const attendee = await createRecord("attendeemanagement", `ctx-attendee-${suffix}`);

  return {
    eventId: event.id,
    attendeeId: attendee.id,
  };
}

const testContexts = {
  report: withContext,
  priorityreport: withContext,
  review: withContext,
  reviewanonymous: withContext,
};

async function cleanupCreatedRecords() {
  for (const item of cleanup.reverse()) {
    try {
      await deleteRecord(item.name, item.id);
    } catch (cleanupError) {
      console.error(`cleanup failed for ${item.name}#${item.id}:`, cleanupError.message);
    }
  }
  cleanup.length = 0;
}

async function main() {
  await request("POST", "eventcreation", "list", {});

  const selectedModules = parseModules();

  if (selectedModules.length === 0) {
    return;
  }

  console.log(`Running CRUD tests for: ${selectedModules.join(", ")}`);

  const failures = [];

  async function runCase(name) {
    const makeContext = testContexts[name] ?? (async () => ({}));

    try {
      await crud(name, await makeContext());
    } catch (error) {
      failures.push({ name, error });
      console.error(`not ok - ${name} CRUD`);
      console.error(error.message);
    }
  }

  for (const name of selectedModules) {
    await runCase(name);
  }

  await cleanupCreatedRecords();

  if (failures.length > 0) {
    throw new Error(
      `${failures.length} CRUD test(s) failed: ${failures
        .map((failure) => failure.name)
        .join(", ")}`,
    );
  }
}

main()
  .catch(async (error) => {
    console.error(error);
    await cleanupCreatedRecords();
    process.exitCode = 1;
  });