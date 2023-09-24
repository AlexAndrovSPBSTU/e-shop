<template>
    <v-breadcrumbs :items="items" divider="/"></v-breadcrumbs>
</template>

<script>
    export default {
        props: ['items']
    }
</script>